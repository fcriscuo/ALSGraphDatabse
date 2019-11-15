package org.biodatagraphdb.alsdb.consumer;


import com.twitter.util.Function3;
import java.nio.file.Path;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import org.biodatagraphdb.alsdb.supplier.GraphDatabaseServiceSupplier;
import edu.jhu.fcriscu1.als.graphdb.util.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import edu.jhu.fcriscu1.als.graphdb.util.StringUtils;
import scala.Tuple2;
import scala.Tuple3;

public abstract class GraphDataConsumer implements Consumer<Path> {



  protected final String HUMAN_SPECIES = "homo sapiens";

  // defined Labels
  protected org.biodatagraphdb.alsdb.lib.FunctionLib lib;
  protected final Label alsAssociatedLabel = new DynamicLabel("ALS-associated");
  protected final Label subjectLabel  = new DynamicLabel("Subject");
  protected final Label sampleLabel  = new DynamicLabel("Sample");
  protected final Label tissueLabel = new DynamicLabel("Tissue");
  protected final Label pathwayLabel = new DynamicLabel("Pathway");
  protected final Label diseaseLabel = new DynamicLabel("Disease");
  protected final Label rnaTpmLabel = new DynamicLabel("RnaTpm");
  protected final Label geneticEntityLabel = new DynamicLabel("GeneticEntity");
  protected final Label proteinLabel  = new DynamicLabel("Protein");
  protected final Label drugBankLabel = new DynamicLabel("DrugBank");
  protected final Label transcriptLabel = new DynamicLabel("Transcript");
  protected final Label geneLabel = new DynamicLabel("Gene");
  protected final Label geneOntologyLabel = new DynamicLabel("GeneOntology");
  protected final Label sampleVariantLabel = new DynamicLabel("SampleVariant");
  protected final Label snpLabel = new DynamicLabel("SNP");
  protected final Label neurobankLabel = new DynamicLabel("Neurobank");
  protected final Label proactLabel = new DynamicLabel("PRO-ACT");
  protected final Label neurobankCategoryLabel = new DynamicLabel("NeurobankCategory");
  protected final Label subjectPropertyLabel = new DynamicLabel("SubjectProperty");
  protected final Label alsStudyTimepointLabel = new DynamicLabel("AlsStudyTimepoint");
  protected final Label alsStudyTimepointEventLabel = new DynamicLabel("AlsStudyTimepointEvent");
  protected final Label subjectEventPropertyLabel = new DynamicLabel("SubjectEventProperty");
  protected final Label subjectEventPropertyValueLabel = new DynamicLabel("SubjectEventPropertyValue");
  protected final Label xrefLabel = new DynamicLabel("Xref");
  protected final Label hgncLabel = new DynamicLabel("HGNC");
  protected final Label ensemblLabel = new DynamicLabel("ensembl");
  protected final Label pubMedLabel = new DynamicLabel("PubMed");
  protected final Label ccdsLabel = new DynamicLabel("CCDS");
  protected final Label entrezLabel = new DynamicLabel("Entrez");
  protected final Label omimLabel = new DynamicLabel("Omim");
  protected final Label refSeqLabel = new DynamicLabel("RefSeq");
  protected final Label alsodMutationLabel = new DynamicLabel("ALSoDMutation");
  protected final Label proteinCodingLabel = new DynamicLabel("ProteinCodingGene");
  protected final Label nonCodingRNALabel = new DynamicLabel("Non-codingRNA");

  protected final Label proactAdverseEventLabel = new DynamicLabel("AdverseEvent");
  protected final Label systemOrganClassLabel = new DynamicLabel("SystemOrganClass");
  protected final Label highLevelGroupTermLabel = new DynamicLabel("HighLevelGroupTerm");
  protected final Label highLevelTermLabel = new DynamicLabel("HighLevelTerm");
  protected final Label preferredTermLabel = new DynamicLabel("PreferredTerm");
  protected final Label lowestLevelTermLabel = new DynamicLabel("LowestLevelTerm");
 // defined relationship types
  protected final RelationshipType transcribesRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("TRANSCRIBES");
  protected final RelationshipType xrefRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("REFERENCES");
  protected final RelationshipType encodedRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("ENCODED_BY");
  protected final RelationshipType noEventRealtionshipType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("NO_EVENT");
  protected final RelationshipType pathwayRelationshipType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IN_PATHWAY");
  protected final RelationshipType biomarkerRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_BIOMARKER");
  protected final RelationshipType therapeuticRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_THERAPEUTIC");
  protected final RelationshipType geneticVariationRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_GENETIC_VARIATION");
  protected final RelationshipType alsAssoctiatedRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_ALS_ASSOCIATED");
  protected final RelationshipType ppiAssociationRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("ASSOCIATES");
  protected final RelationshipType ppiColocalizationRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("CO-LOCALIZES");
  protected final RelationshipType ppiGeneticInteractionRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_GENETIC_INTERACTION");
  protected final RelationshipType ppiPredictedInteractionRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_PREDICTED_INTERACTION");
  protected final RelationshipType tissueEnhancedRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_ENHANCED_IN");
  protected final RelationshipType drugTargetRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_DRUG_TARGET");
  protected final RelationshipType drugEnzymeRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_DRUG_ENZYME");
  protected final RelationshipType drugTransporterRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_DRUG_TRANSPORTER");
  protected final RelationshipType drugCarrierRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_DRUG_CARRIER");
  protected final RelationshipType partOfRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_PART_OF");
  protected final RelationshipType degRealtedToRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_DEG_RELATED_TO");
  protected final RelationshipType seqSimRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS SEQ_SIMILARITY_TO");
  protected final RelationshipType goClassificatioRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_GO_CLASSIFICATION");
  protected final RelationshipType transcriptRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("TRANSCRIBES");
  protected final RelationshipType implicatedInRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_IMPLICATED_IN");
  protected final RelationshipType sampleRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_SAMPLE");
  protected final RelationshipType sampledFromRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("SAMPLED_FROM");
  protected final RelationshipType mapsToRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("MAPS_TO");
  protected final RelationshipType expressionLevelRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_EXPRESS_LEVEL");
  protected final RelationshipType expressedProteinRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("EXPRESSES_PROTEIN");
  protected final RelationshipType associatedProteinRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("ASSOCIATED_PROTEIN");
  protected final RelationshipType geneticEntityRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("ASSOCIATED_GENETIC_ENTITY");
  protected final RelationshipType variantRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("ASSOCIATED_VARIANT");
  protected final RelationshipType childRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("IS_CHILD_OF");
  protected final RelationshipType categorizesRelType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("CATEGORIZES");
  protected final RelationshipType propertyRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_PROPERTY");
  protected final RelationshipType subjectEventRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_SUBJECT_EVENT");
  protected final RelationshipType timepointtRelationType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("OCCURRED_AT");
  protected final RelationshipType goBioProcessRelType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_GO_BIO_PROCESS");
  protected final RelationshipType goCellComponentRelType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_GO_CELLULAR_COMPONENT");
  protected final RelationshipType goMolFunctionRelType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_GO_MOLECULAR_FUNCTION");
  protected final RelationshipType pubMedXrefRelType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes("HAS_PUBMED_XREF");

    protected final RelationshipType eventValueSubjectRelType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes(
            "HAS_PROPERTY");
    protected final RelationshipType eventValueEventRelType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes(
            "HAS_EVENT_OCCURRANCE"
    );
    protected final RelationshipType eventTimepointRelType = new org.biodatagraphdb.alsdb.util.DynamicRelationshipTypes(
            "OCCURRED_AT"
    );


  private GraphDatabaseServiceSupplier.RunMode runMode;
  protected  GraphDataConsumer(@Nonnull GraphDatabaseServiceSupplier.RunMode runMode) {
    this.lib = new org.biodatagraphdb.alsdb.lib.FunctionLib(runMode);
    this.runMode = runMode;
  }

  public Supplier<GraphDatabaseServiceSupplier.RunMode> consumerRunModeSupplier = () -> this.runMode;


  protected Function<String,Node> resolveProactAdverseEventNode = (id) ->{
   Node aeNode = lib.getResolveGraphNodeFunction().apply(new Tuple3<>(proactAdverseEventLabel,
     "AdverseEventId",id  ));
   lib.getNovelLabelConsumer().accept(aeNode, alsAssociatedLabel);
   lib.getNovelLabelConsumer().accept(aeNode, proactLabel);
   return aeNode;
 };


  /*
  Consumer that ensures that an ALS-associated Node is properly annotated
   */
  protected Consumer<Node> annotateNeurobankNodeConsumer = (node)-> {
    lib.getNovelLabelConsumer().accept(node, neurobankLabel);
    lib.getNovelLabelConsumer().accept(node, alsAssociatedLabel);
  };

  protected Function<org.biodatagraphdb.alsdb.value.GeneOntology,Node> resolveGeneOntologyNodeFunction = (go)-> {
    Node goNode = lib.getResolveGraphNodeFunction()
        .apply(new Tuple3<>(geneOntologyLabel,"GeneOntology",go.goTermAccession()));
    lib.getNovelLabelConsumer().accept(goNode,xrefLabel);
    if (org.biodatagraphdb.alsdb.value.GeneOntology.isValidString(go.goDomain())) {
      lib.getNovelLabelConsumer().accept(goNode, new DynamicLabel(go.goDomain()));
    }
    lib.getNodePropertyValueConsumer().accept(goNode, new Tuple2<>("GeneOntologyTermAccession", go.goTermAccession()));
    lib.getNodePropertyValueConsumer().accept(goNode, new Tuple2<>("GeneOntologyDomain",
        go.goDomain()));
    lib.getNodePropertyValueConsumer().accept(goNode, new Tuple2<>("GeneOntologyTermName", go.goName()));
    lib.getNodePropertyValueConsumer().accept(goNode, new Tuple2<>("GeneOntologyTermDefinition", go.goDefinition()));
    return goNode;
  };

  protected Function<String,Node> resolveAlsodMutationNodeFunction  = (id)->
      lib.getResolveGraphNodeFunction().apply(new Tuple3<>(alsodMutationLabel, "ALSoDMutationCode", id));

  protected Function<String,Node> resolveSubjectEventPropertyValueNodeFunction = (id) ->
      lib.getResolveGraphNodeFunction()
          .apply(new Tuple3<>(subjectEventPropertyValueLabel,"EventPropertyValue",id));

  protected Function<String,Node> resolveSubjectEventPropertyNodeFunction = (id) ->
    lib.getResolveGraphNodeFunction().apply(new Tuple3<>(subjectEventPropertyLabel,"EventProperty",id));

  /*
  Function to find or create a new Xref Node
  Requires a secondary Label to identify the xref source
   */
  protected BiFunction<Label,String,Node> resolveXrefNode = (label, id)-> {
    Node node = lib.getResolveGraphNodeFunction().apply(new Tuple3<>(xrefLabel, "XrefId", id));
    lib.getNovelLabelConsumer().accept(node, label);
    return node;
  };

  /*
A Protected Function that will find/create an Xref Node using a specified identifier and the
standard xref Label. The supplied Label will be added if the xref Node does not already have that
Label. A xref Relationship, between the supplied Node and the resolved xref node, will
be created and returned
 */
  protected Function3<Node,Label,String, Relationship> registerXrefRelationshipFunction
      = new Function3<Node, Label, String, Relationship>() {
    @Override
    public Relationship apply(Node sourceNode, Label secondaryLabel, String xrefId) {
      Node xrefNode = resolveXrefNode.apply(xrefLabel,xrefId);
      lib.getNovelLabelConsumer().accept(xrefNode,secondaryLabel);
      return lib.getResolveNodeRelationshipFunction().apply(new Tuple2<>(sourceNode,xrefNode),xrefRelationType);
    }
  };

  protected Function<Tuple2<String,String>, Node>  resolveEventTimepointNodeFunction = (tuple) ->{
    Node node =  lib.getResolveGraphNodeFunction().apply(new Tuple3<>(alsStudyTimepointLabel,"TimepointId",tuple._1()));
    lib.getNodePropertyValueConsumer().accept(node, new Tuple2<>("TimepintName",tuple._2()));
    return node;
  };
  /*
  resolveSubjectEventNodeFunction.apply(property.subjectEventTuple());
   */
  protected Function<Tuple2<String,String>,Node> resolveSubjectEventNodeFunction = (tuple) -> {
    Node node = lib.getResolveGraphNodeFunction().apply(new Tuple3<>(alsStudyTimepointEventLabel,
        "EventCategory",tuple._1()));
    lib.getNodePropertyValueConsumer().accept(node, new Tuple2<>("FormName", tuple._2()));
    return node;
  };



  protected Function<String,Node> resolveSubjectPropertyNode = (id) ->
      lib.getResolveGraphNodeFunction().apply(new Tuple3<>(subjectPropertyLabel, "Id",id));

  protected Function<String,Node> resolveCategoryNode = (category) ->
      lib.getResolveGraphNodeFunction().apply(new Tuple3<>(neurobankCategoryLabel, "Category",category));

  protected Function<Tuple2<String,String>, Node> resolveSubjectNodeFunction =
      (subjectTuple) -> {
        Node node = lib.getResolveGraphNodeFunction().apply(new Tuple3<>(subjectLabel,
            "SubjectGuid", subjectTuple._2()));
        lib.getNodePropertyValueConsumer().accept(node, new Tuple2<>("SubjectId", subjectTuple._1()));
        return node;
      };



  protected Function<String, Node> resolveHumanTissueNodeFunction = (tissueId) ->
      lib.getResolveGraphNodeFunction().apply(new Tuple3<>(tissueLabel,
          "TissueId",tissueId));

  protected Function<String, Node> resolvePathwayNodeFunction = (pathwayId) ->
      lib.getResolveGraphNodeFunction().apply(new Tuple3<>(pathwayLabel,
          "PathwayId",pathwayId));


  protected Function<String, Node> resolveDiseaseNodeFunction = (diseaseId) ->
      lib.getResolveGraphNodeFunction().apply(new Tuple3<>(diseaseLabel,
          "DiseaseId",diseaseId));

  protected Function<org.biodatagraphdb.alsdb.value.RnaTpmGene, Node> resolveRnaTpmGeneNode = (rnaTpmGene) -> {
    Node node = lib.getResolveGraphNodeFunction().apply(new Tuple3<>(rnaTpmLabel,
        "RnaTpmId", rnaTpmGene.id()));
    if (node != null ) {
      // persist tpm value as a String
      lib.getNodePropertyValueConsumer()
          .accept(node, new Tuple2<>("TPM", String.valueOf(rnaTpmGene.tpm())));
    }
    return node;
  };

  protected Function<String,Node> resolveSampleNodeFunction = (sampleId) ->
      lib.getResolveGraphNodeFunction().apply(new Tuple3<>(sampleLabel, "SampleId", sampleId));


  protected Function<String, Node> resolveGeneticEntityNodeFunction = (geneticEntityId) ->
     lib.getResolveGraphNodeFunction()
         .apply(new Tuple3<>(geneticEntityLabel,"GeneticEntityId", geneticEntityId));
  /*
  Protected Function that resolves a Protein Node for a specified UniProt id
  by either finding an existing Node or by creating a new one
   */
  protected Function<String, Node> resolveProteinNodeFunction = (uniprotId) ->
      lib.getResolveGraphNodeFunction().apply(new Tuple3<>(proteinLabel,"UniProtKBID", uniprotId));


  protected Function<String, Node> resolveDrugBankNode = (dbId) ->
      lib.getResolveGraphNodeFunction().apply(new Tuple3<>(drugBankLabel,"DrugBankId", dbId));

  protected Function<String, Node> resolveEnsemblTranscriptNodeFunction =
      transcriptId -> {
        Node node = resolveGeneticEntityNodeFunction.apply(transcriptId);
        lib.getNovelLabelConsumer().accept(node,transcriptLabel);
        return node;
      };

  protected Function<String, Node> resolveEnsemblGeneNodeFunction =
      geneId -> {
        Node node = resolveGeneticEntityNodeFunction.apply(geneId);
        lib.getNovelLabelConsumer().accept(node,geneLabel);
        return node;
      };

  protected void createEnsemblTranscriptNodes(@Nonnull org.biodatagraphdb.alsdb.value.UniProtValue upv) {
    String uniprotId = upv.uniprotId();
    StringUtils.convertToJavaString(upv.ensemblTranscriptList()).forEach(transcriptId -> {
      Node transcriptNode = resolveEnsemblTranscriptNodeFunction.apply(transcriptId);
      Node proteinNode = resolveProteinNodeFunction.apply(uniprotId);
      Tuple2<String, String> key = new Tuple2<>(uniprotId, transcriptId);
      lib.getResolveNodeRelationshipFunction().apply(new Tuple2<>(proteinNode, transcriptNode),
          encodedRelationType );
    });
  }
protected Function<org.biodatagraphdb.alsdb.value.SampleVariantSummary,Node> resolveSampleVariantNode = (svc) -> {

  Node svNode = lib.getResolveGraphNodeFunction()
      .apply(new Tuple3<>(sampleVariantLabel,"SampleVariantId", svc.id()));
  // persist the list of variants
  lib.getNodePropertyValueStringArrayConsumer().accept(svNode,new Tuple2<>("Variants", svc.variantList()));
  return svNode;
};

  protected Function<String, Node> resolveSnpNodeFunction = (snpId) ->
      lib.getResolveGraphNodeFunction().apply(new Tuple3<>(snpLabel,"SNP",snpId));

}

