package edu.jhu.fcriscu1.als.graphdb.consumer;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import edu.jhu.fcriscu1.als.graphdb.integration.TestGraphDataConsumer;
import edu.jhu.fcriscu1.als.graphdb.supplier.GraphDatabaseServiceSupplier;
import edu.jhu.fcriscu1.als.graphdb.value.AlsPropertyCategory;
import org.neo4j.graphdb.Node;
import edu.jhu.fcriscu1.als.graphdb.util.AsyncLoggingService;
import edu.jhu.fcriscu1.als.graphdb.util.FrameworkPropertyService;
import edu.jhu.fcriscu1.als.graphdb.util.TsvRecordStreamSupplier;
import scala.Tuple2;

/*
Consumer responsible for loading Neurobank categories into the Neo4j
database.
This Consumer should be invoked before other Neurobank data are loaded
 */
public class NeurobankCategoryConsumer extends GraphDataConsumer {

  public NeurobankCategoryConsumer(GraphDatabaseServiceSupplier.RunMode runMode) {super(runMode);}

  private Consumer<AlsPropertyCategory> neurobankCategoryConsumer = (category) -> {
    Node categoryNode = resolveCategoryNode.apply(category.category());
    if(!category.isSelfReferential()){
      Node parentCategoryNode = resolveCategoryNode.apply(category.parentCategory());
      lib.resolveNodeRelationshipFunction.apply(new Tuple2<>(parentCategoryNode, categoryNode),
          childRelationType);
    }
  };

  @Override
  public void accept(Path path) {
    Preconditions.checkArgument(path != null);
    new TsvRecordStreamSupplier(path).get()
        .map(AlsPropertyCategory::parseCSVRecord)
        .forEach(neurobankCategoryConsumer);
    lib.shutDown();
  }
  public static void importProdData() {
    Stopwatch sw = Stopwatch.createStarted();
    FrameworkPropertyService.INSTANCE
        .getOptionalPathProperty("ALS_PROPERTY_CATEGORY_FILE")
        .ifPresent(new NeurobankCategoryConsumer(GraphDatabaseServiceSupplier.RunMode.PROD));
    AsyncLoggingService.logInfo("processed neurobank category file : " +
        sw.elapsed(TimeUnit.SECONDS) +" seconds");
  }
  // run this Consumer independently
  public static void main(String[] args) {

    FrameworkPropertyService.INSTANCE
        .getOptionalPathProperty("ALS_PROPERTY_CATEGORY_FILE")
        .ifPresent(
            path -> new TestGraphDataConsumer().accept(path, new NeurobankCategoryConsumer(GraphDatabaseServiceSupplier.RunMode.TEST)));
  }
}