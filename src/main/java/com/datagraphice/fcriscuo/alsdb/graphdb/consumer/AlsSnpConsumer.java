package com.datagraphice.fcriscuo.alsdb.graphdb.consumer;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.datagraphice.fcriscuo.alsdb.graphdb.integration.TestGraphDataConsumer;
import com.datagraphice.fcriscuo.alsdb.graphdb.supplier.GraphDatabaseServiceSupplier;
import edu.jhu.fcriscu1.als.graphdb.value.EnsemblAlsSnp;
import org.neo4j.graphdb.Node;
import edu.jhu.fcriscu1.als.graphdb.util.AsyncLoggingService;
import scala.Tuple2;

/*
Java Consumer responsible for importing SNPs for
ALS-associated genes from data downloaded from ensembl
It is assumed that this consumer will be invoked AFTER
the AlsGeneConsumer, so protein, gene, and transcript nodes will
have been created
 */
public class AlsSnpConsumer  extends GraphDataConsumer{

  public AlsSnpConsumer(GraphDatabaseServiceSupplier.RunMode runMode){
    super(runMode);
  }

  private Consumer<EnsemblAlsSnp> alsSnpConsumer = (snp)-> {
    Node snpNode = resolveSnpNodeFunction.apply(snp.variantId());
    // add ALS label if these nodes have not been already labeled
    lib.getNovelLabelConsumer().accept(snpNode, alsAssociatedLabel);
    // set/reset SNP properties
    lib.getNodeIntegerPropertyValueConsumer().accept(snpNode, new Tuple2<>("DistanceToTranscript", snp.distance()));
    lib.getNodePropertyValueConsumer().accept(snpNode, new Tuple2<>("VariantAlleles", snp.alleleVariation()));
    // this will create a Transcript Node if run in stand-alone test mode
    Node transcriptNode = resolveEnsemblTranscriptNodeFunction.apply(snp.ensemblTranscriptId());
    // establish a relationship between transcript and snp
    lib.getResolveNodeRelationshipFunction().apply(new Tuple2<>(transcriptNode,snpNode) ,
        geneticEntityRelationType);
  };

  @Override
  public void accept(Path path) {
    Preconditions.checkArgument(path != null);
    new com.datagraphice.fcriscuo.alsdb.graphdb.util.TsvRecordStreamSupplier(path).get()
        .map(EnsemblAlsSnp::parseCSVRecord)
        .forEach(alsSnpConsumer);
    lib.shutDown();
  }
  public static void importProdData() {
    Stopwatch sw = Stopwatch.createStarted();
    com.datagraphice.fcriscuo.alsdb.graphdb.util.FrameworkPropertyService.INSTANCE
        .getOptionalPathProperty("ENSEMBL_ALS_SNP_FILE")
        .ifPresent(new AlsSnpConsumer(GraphDatabaseServiceSupplier.RunMode.PROD));
    AsyncLoggingService.logInfo("processed ensembl als snp file : " +
        sw.elapsed(TimeUnit.SECONDS) +" seconds");
  }

  // stand alone test
  public static void main(String[] args) {
    com.datagraphice.fcriscuo.alsdb.graphdb.util.FrameworkPropertyService.INSTANCE
        .getOptionalPathProperty("TEST_ENSEMBL_ALS_SNP_FILE")
        .ifPresent(path -> new TestGraphDataConsumer().accept(path, new AlsSnpConsumer(GraphDatabaseServiceSupplier.RunMode.TEST)));
  }
}
