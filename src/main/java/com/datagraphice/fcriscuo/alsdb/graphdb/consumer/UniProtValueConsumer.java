package com.datagraphice.fcriscuo.alsdb.graphdb.consumer;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.datagraphice.fcriscuo.alsdb.graphdb.integration.TestGraphDataConsumer;
import com.datagraphice.fcriscuo.alsdb.graphdb.supplier.GraphDatabaseServiceSupplier;
import edu.jhu.fcriscu1.als.graphdb.value.GeneOntology;
import edu.jhu.fcriscu1.als.graphdb.value.UniProtValue;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import edu.jhu.fcriscu1.als.graphdb.util.AsyncLoggingService;
import edu.jhu.fcriscu1.als.graphdb.util.StringUtils;
import scala.Tuple2;

public class UniProtValueConsumer extends GraphDataConsumer {

  public UniProtValueConsumer(GraphDatabaseServiceSupplier.RunMode runMode) {super(runMode);}
  @Override
  public void accept(Path path) {
    Preconditions.checkArgument(path != null);
    new com.datagraphice.fcriscuo.alsdb.graphdb.util.TsvRecordStreamSupplier(path).get()
        .map(UniProtValue::parseCSVRecord)
        //.filter(upv->UniProtValue.isValidString(upv.uniprotId()))
        .forEach(uniProtValueConsumer);
    lib.shutDown();
  }

  private void createProteinGeneOntologyRealtionship(String uniprotId, GeneOntology go, RelationshipType relType) {
    // establish relationship to the protein node
      Node goNode = resolveGeneOntologyNodeFunction.apply(go);
      Node proteinNode = resolveProteinNodeFunction.apply(uniprotId);

      lib.getResolveNodeRelationshipFunction().apply(new Tuple2<>(proteinNode, goNode),
          relType);
      AsyncLoggingService.logInfo("Created relationship between protein " + uniprotId
          + " and GO id: " + go.goTermAccession());
  }

  private Consumer<UniProtValue> geneOntologyListConsumer = (upv) -> {
    // complete the association with gene ontology links
    // biological process
    StringUtils.convertToJavaString(upv.goBioProcessList())
        .stream()
        .map(goEntry -> GeneOntology.parseGeneOntologyEntry("Gene Ontology (bio process)", goEntry))
        .forEach(go -> createProteinGeneOntologyRealtionship(upv.uniprotId(), go,
           goBioProcessRelType));
    // cellular  component
    StringUtils.convertToJavaString(upv.goCellComponentList())
        .stream()
        .map(goEntry -> GeneOntology
            .parseGeneOntologyEntry("Gene Ontology (cellular component)", goEntry))
        .forEach(go -> createProteinGeneOntologyRealtionship(upv.uniprotId(), go,
            goCellComponentRelType));
    // molecular function
    StringUtils.convertToJavaString(upv.goMolFuncList())
        .stream()
        .map(
            goEntry -> GeneOntology.parseGeneOntologyEntry("Gene Ontology (mol function)", goEntry))
        .forEach(go -> createProteinGeneOntologyRealtionship(upv.uniprotId(), go,
            goMolFunctionRelType));

  };

  private Consumer<UniProtValue> uniProtValueToProteinNodeConsumer = (upv) -> {
      Node node = resolveProteinNodeFunction.apply(upv.uniprotId());
      lib.getNodePropertyValueConsumer().accept(node, new Tuple2<>("UniProtName", upv.uniprotName()));
      lib.getNodePropertyValueListConsumer().accept(node, new Tuple2<>("ProteinName", upv.proteinNameList()));
      lib.getNodePropertyValueListConsumer().accept(node, new Tuple2<>("GeneSymbol", upv.geneNameList()));
      lib.getNodePropertyValueConsumer().accept(node, new Tuple2<>("Mass", upv.mass()));
    lib.getNodePropertyValueConsumer().accept(node, new Tuple2<>("Length", upv.length()));
      AsyncLoggingService.logInfo(">>>Created Protein node for " +upv.uniprotId());

  };

  // create drugbank nodes associated with this protein
  // to protein - drug relationships are determined by other
  // source files
  private Consumer<UniProtValue> drugBankIdConsumer = (upv) ->
      StringUtils.convertToJavaString(upv.drugBankIdList())
      .forEach(dbId ->resolveDrugBankNode.apply(dbId));

  private Consumer<UniProtValue> pubMedXrefConsumer = (upv) -> {
    // PubMed Xrefs
    Node proteinNode = resolveProteinNodeFunction.apply(upv.uniprotId());
    StringUtils.convertToJavaString(upv.pubMedIdList())
        .stream()
        .map(pubMedId ->resolveXrefNode.apply(pubMedLabel,pubMedId))
        .forEach(xrefNode -> lib.getResolveNodeRelationshipFunction().apply(new Tuple2<>(proteinNode, xrefNode),
            pubMedXrefRelType));
  };

  private Consumer<UniProtValue> uniProtValueConsumer = (upv -> {
    // create the protein node for this uniprot entry
    uniProtValueToProteinNodeConsumer.accept(upv);
    // add Gene Ontology associations
    geneOntologyListConsumer.accept(upv);
    // add ensembl trancripts
    //createEnsemblTranscriptNodes(upv);
    // add drugs associated with this protein
    drugBankIdConsumer.accept(upv);
    // pubmed
    pubMedXrefConsumer.accept(upv);

  });
  public static void importProdData() {
    Stopwatch sw = Stopwatch.createStarted();
    com.datagraphice.fcriscuo.alsdb.graphdb.util.FrameworkPropertyService.INSTANCE.getOptionalPathProperty("UNIPROT_HUMAN_FILE")
        .ifPresent(new UniProtValueConsumer(GraphDatabaseServiceSupplier.RunMode.PROD));
    AsyncLoggingService.logInfo("read uniprot data: " +
        sw.elapsed(TimeUnit.SECONDS) +" seconds.");
  }

  public static void main(String[] args) {

    com.datagraphice.fcriscuo.alsdb.graphdb.util.FrameworkPropertyService.INSTANCE.getOptionalPathProperty("TEST_UNIPROT_HUMAN_FILE")
        .ifPresent(path ->
            new TestGraphDataConsumer().accept(path, new UniProtValueConsumer(GraphDatabaseServiceSupplier.RunMode.TEST)));
  }


}
