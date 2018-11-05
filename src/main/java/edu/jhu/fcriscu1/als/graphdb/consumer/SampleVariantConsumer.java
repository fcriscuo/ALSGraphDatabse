package edu.jhu.fcriscu1.als.graphdb.consumer;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import edu.jhu.fcriscu1.als.graphdb.integration.TestGraphDataConsumer;
import edu.jhu.fcriscu1.als.graphdb.supplier.GraphDatabaseServiceSupplier;
import edu.jhu.fcriscu1.als.graphdb.util.DynamicRelationshipTypes;
import edu.jhu.fcriscu1.als.graphdb.value.SampleVariantSummary;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import edu.jhu.fcriscu1.als.graphdb.util.AsyncLoggingService;
import edu.jhu.fcriscu1.als.graphdb.util.FrameworkPropertyService;
import edu.jhu.fcriscu1.als.graphdb.util.TsvRecordSplitIteratorSupplier;
import scala.Tuple2;
/*
Java Consumer responsible for importing sample variant data
Creates new Sample variant nodes
A SampleVariantNode will be specific for each gene in each sample
and a List of variants as a CSV String
 */

public class SampleVariantConsumer extends GraphDataConsumer{

  public SampleVariantConsumer(GraphDatabaseServiceSupplier.RunMode runMode) {super(runMode);}

 private Consumer<SampleVariantSummary> sampleVariantSummaryConsumer = (svc) -> {

   // this is the only class that will create SampleVariant nodes and
   // their relationships
   Node geneNode = resolveEnsemblGeneNodeFunction.apply(svc.ensemblGeneId());
   Node sampleNode = resolveSampleNodeFunction.apply(svc.extSampleId());
   Node sampleVariantNode = resolveSampleVariantNode.apply(svc);
   if (lib.isAlsAssociatedPredicate.test(geneNode)) {
     lib.novelLabelConsumer.accept(sampleVariantNode, alsAssociatedLabel);
     lib.novelLabelConsumer.accept(sampleNode, alsAssociatedLabel);
   }
   // create sample <-> sampleVariant relationship
   Relationship sampleToSampleVariantRel = lib.resolveNodeRelationshipFunction.apply(new Tuple2<>(sampleNode,sampleVariantNode),
       new DynamicRelationshipTypes("sample_variant"));
   lib.setRelationshipIntegerProperty.accept(sampleToSampleVariantRel, new Tuple2<>("VariantCount", svc.numVariants()));

   lib.resolveNodeRelationshipFunction.apply(new Tuple2<>(sampleVariantNode,geneNode),
      encodedRelationType);
 };

  @Override
  public void accept(Path path) {
    Preconditions.checkArgument(null != path
        && Files.exists(path, LinkOption.NOFOLLOW_LINKS));
    new TsvRecordSplitIteratorSupplier(path, SampleVariantSummary.columnHeadings())
        .get()
        .map(SampleVariantSummary::parseCSVRecord)
        .forEach(sampleVariantSummaryConsumer);
    lib.shutDown();

  }
  public static void importProdData() {
    Stopwatch sw = Stopwatch.createStarted();
    FrameworkPropertyService.INSTANCE
        .getOptionalPathProperty("SAMPLE_VARIANT_SUMUMMARY_FILE")
        .ifPresent(new SampleVariantConsumer(GraphDatabaseServiceSupplier.RunMode.PROD));
    AsyncLoggingService.logInfo("processed complete sample variant file : " +
        sw.elapsed(TimeUnit.SECONDS) +" seconds");
  }
  public static void main(String[] args) {
    FrameworkPropertyService.INSTANCE
        .getOptionalPathProperty("TEST_SAMPLE_VARIANT_SUMUMMARY_FILE")
        .ifPresent(path -> new TestGraphDataConsumer().accept(path, new SampleVariantConsumer(GraphDatabaseServiceSupplier.RunMode.TEST)));
  }

}