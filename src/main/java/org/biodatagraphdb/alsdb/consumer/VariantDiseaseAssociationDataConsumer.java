package org.biodatagraphdb.alsdb.consumer;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.biodatagraphdb.alsdb.integration.TestGraphDataConsumer;
import org.biodatagraphdb.alsdb.supplier.GraphDatabaseServiceSupplier;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.biodatagraphdb.alsdb.util.AsyncLoggingService;
import scala.Tuple2;

public class VariantDiseaseAssociationDataConsumer extends GraphDataConsumer{
  public VariantDiseaseAssociationDataConsumer(GraphDatabaseServiceSupplier.RunMode runMode) {super(runMode);}

  private Consumer<org.biodatagraphdb.alsdb.value.VariantDiseaseAssociation> variantDiseaseAssociationConsumer = (snp) ->{
    Node diseaseNode = resolveDiseaseNodeFunction.apply(snp.diseaseId());
    // set or reset disease name
    lib.nodePropertyValueConsumer.accept(diseaseNode, new Tuple2<>("DiseaseName", snp.diseaseName()));
    Node snpNode = resolveSnpNodeFunction.apply(snp.snpId());
    // create  relationship between snp & disease
    Relationship rel = lib.resolveNodeRelationshipFunction.apply(new Tuple2<>(snpNode, diseaseNode),
        implicatedInRelationType);
    lib.relationshipPropertyValueConsumer.accept(rel, new Tuple2<>("ConfidenceLevel",String.valueOf(snp.score())));
    lib.relationshipPropertyValueConsumer.accept(rel, new Tuple2<>("Reference",snp.source()));
  };
  @Override
  public void accept(Path path) {
    Preconditions.checkArgument(Files.exists(path, LinkOption.NOFOLLOW_LINKS));
    new org.biodatagraphdb.alsdb.util.TsvRecordStreamSupplier(path)
        .get()
        .map(org.biodatagraphdb.alsdb.value.VariantDiseaseAssociation::parseCSVRecord)
        .forEach(variantDiseaseAssociationConsumer);
    lib.shutDown();
  }

  public static void importProdData() {
    Stopwatch sw = Stopwatch.createStarted();
    org.biodatagraphdb.alsdb.util.FrameworkPropertyService.INSTANCE
        .getOptionalPathProperty("VARIANT_DISEASE_ASSOC_DISGENET_FILE")
        .ifPresent(new VariantDiseaseAssociationDataConsumer(GraphDatabaseServiceSupplier.RunMode.PROD));
    AsyncLoggingService.logInfo("processed variant disease associaton file : " +
        sw.elapsed(TimeUnit.SECONDS) +" seconds");
  }
  // main method for stand alone testing
  public static void main(String[] args) {
    org.biodatagraphdb.alsdb.util.FrameworkPropertyService.INSTANCE.getOptionalPathProperty("TEST_VARIANT_DISEASE_ASSOC_DISGENET_FILE")
        .ifPresent(path ->
            new TestGraphDataConsumer().accept(path,new VariantDiseaseAssociationDataConsumer(GraphDatabaseServiceSupplier.RunMode.TEST)));
  }

}
