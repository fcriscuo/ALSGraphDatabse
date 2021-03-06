package com.datagraphice.fcriscuo.alsdb.graphdb.consumer;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.datagraphice.fcriscuo.alsdb.graphdb.integration.TestGraphDataConsumer;
import com.datagraphice.fcriscuo.alsdb.graphdb.supplier.GraphDatabaseServiceSupplier;
import edu.jhu.fcriscu1.als.graphdb.value.UniProtBlastResult;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import edu.jhu.fcriscu1.als.graphdb.util.AsyncLoggingService;
import scala.Tuple2;

public class UniprotBlastResultConsumer extends GraphDataConsumer {

  public UniprotBlastResultConsumer(GraphDatabaseServiceSupplier.RunMode runMode) {
    super(runMode);
  }

  private Consumer<UniProtBlastResult> uniProtBlastResultConsumer = (blastResult) -> {

      Node sourceNode = resolveProteinNodeFunction.apply(blastResult.sourceUniprotId());
      Node hitNode = resolveProteinNodeFunction.apply(blastResult.hitUniprotId());
      Tuple2<String, String> keyTuple = new Tuple2<>(blastResult.sourceUniprotId(),
          blastResult.hitUniprotId());
      // create or find existing Relationship pair
      Relationship rel = lib.getResolveNodeRelationshipFunction()
          .apply(new Tuple2<>(sourceNode, hitNode),
              seqSimRelationType);
      lib.getRelationshipPropertyValueConsumer().accept(rel,
          new Tuple2<>("BLAST_score", String.valueOf(blastResult.score())));
      lib.getRelationshipPropertyValueConsumer().accept(rel,
          new Tuple2<>("eValue", blastResult.eValue()));

  };

  @Override
  public void accept(Path path) {
    Preconditions.checkArgument(Files.isRegularFile(path));
    new com.datagraphice.fcriscuo.alsdb.graphdb.util.TsvRecordSplitIteratorSupplier(path, UniProtBlastResult.columnHeadings())
        .get()
        .map(UniProtBlastResult::parseCSVRecord)
        // filter out self similarity
        .filter(blastRes -> !blastRes.sourceUniprotId().equalsIgnoreCase(blastRes.hitUniprotId()))
        .forEach(uniProtBlastResultConsumer);
    lib.shutDown();
  }

  public static void importProdData() {
    Stopwatch sw = Stopwatch.createStarted();
    com.datagraphice.fcriscuo.alsdb.graphdb.util.FrameworkPropertyService.INSTANCE.getOptionalPathProperty("SEQ_SIM_FILE")
        .ifPresent(new UniprotBlastResultConsumer(GraphDatabaseServiceSupplier.RunMode.PROD));
    AsyncLoggingService.logInfo("processed sequence similarity file : " +
        sw.elapsed(TimeUnit.SECONDS) + " seconds");
  }

  // main method for stand alone testing using test data
  public static void main(String[] args) {
    com.datagraphice.fcriscuo.alsdb.graphdb.util.FrameworkPropertyService.INSTANCE
        .getOptionalPathProperty("TEST_SEQ_SIM_FILE")
        .ifPresent(path -> new TestGraphDataConsumer()
            .accept(path, new UniprotBlastResultConsumer(GraphDatabaseServiceSupplier.RunMode.TEST)));
  }

}
