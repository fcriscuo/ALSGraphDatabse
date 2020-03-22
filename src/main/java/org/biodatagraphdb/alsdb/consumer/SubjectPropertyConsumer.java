package org.biodatagraphdb.alsdb.consumer;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.biodatagraphdb.alsdb.integration.TestGraphDataConsumer;
import org.biodatagraphdb.alsdb.model.StringSubjectProperty;
import org.biodatagraphdb.alsdb.service.graphdb.RunMode;
import org.biodatagraphdb.alsdb.supplier.GraphDatabaseServiceLegacySupplier;
import org.neo4j.graphdb.Node;
//import ALSDatabaseImportApp.RelTypes;
import org.biodatagraphdb.alsdb.util.AsyncLoggingService;
import scala.Tuple2;

public class SubjectPropertyConsumer extends GraphDataConsumer {


  public SubjectPropertyConsumer(RunMode runMode) {super(runMode);}

  private Function<StringSubjectProperty, Node> completeSampleNodeFunction = (stringSubjectProperty) -> {
    String externalSampleId = stringSubjectProperty.getExternalSampleId();
    Node sampleNode = resolveSampleNodeFunction.apply(externalSampleId);
    // an existing Sample node may not have had these properties set
    lib.nodePropertyValueConsumer
        .accept(sampleNode, new Tuple2<>("ExternalSampleId", externalSampleId));
    lib.nodePropertyValueConsumer
        .accept(sampleNode, new Tuple2<>("SampleType", stringSubjectProperty.getSampleType()));
    lib.nodePropertyValueConsumer
        .accept(sampleNode, new Tuple2<>("AnalyteType", stringSubjectProperty.getAnalyteType()));
    return sampleNode;
  };

  private Consumer<StringSubjectProperty> stringSubjectPropertyConsumer = (subjectProperty) -> {
    Node subjectNode = resolveSubjectNodeFunction.apply(subjectProperty.getSubjectTuple());
    lib.nodePropertyValueConsumer.accept(subjectNode,
        new Tuple2<>(subjectProperty.getPropertyName(), subjectProperty.getPropertyValue()));
    Node sampleNode = completeSampleNodeFunction.apply(subjectProperty);
    lib.resolveNodeRelationshipFunction.apply(new Tuple2<>(subjectNode,sampleNode),
        sampledFromRelationType );
  };

  private Predicate<org.biodatagraphdb.alsdb.model.StringSubjectProperty> alsPredicate = (ssp) ->
      !ssp.getExternalSubjectId().toUpperCase().startsWith("TCGA");

  /*
  Public Consumer interface method to process the specified file
  as a CSV file containing data that can be mapped to StringSubjectProperty objects
   */
  @Override
  public void accept(Path path) {
    Preconditions.checkArgument(null != path);
    new org.biodatagraphdb.alsdb.util.TsvRecordStreamSupplier(path).get()
        .map(StringSubjectProperty.Companion::parseCSVRecord)
        .forEach(stringSubjectPropertyConsumer);
    lib.shutDown();
  }
  public static void importProdData() {
    Stopwatch sw = Stopwatch.createStarted();
    org.biodatagraphdb.alsdb.util.FrameworkPropertyService.INSTANCE.getOptionalPathProperty("SUBJECT_PROPERTY_FILE")
        .ifPresent(new SubjectPropertyConsumer(RunMode.PROD));
    AsyncLoggingService.logInfo("processed subject properties file: " +
        sw.elapsed(TimeUnit.SECONDS) +" seconds");
  }
  public static void main(String... args) {

    org.biodatagraphdb.alsdb.util.FrameworkPropertyService.INSTANCE
        .getOptionalPathProperty("TEST_SUBJECT_PROPERTY_FILE")
        .ifPresent(path -> new TestGraphDataConsumer().accept(path, new SubjectPropertyConsumer(RunMode.TEST)));
  }
}
