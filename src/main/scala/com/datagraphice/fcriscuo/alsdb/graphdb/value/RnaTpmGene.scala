package edu.jhu.fcriscu1.als.graphdb.value

import java.util.Optional

import com.datagraphice.fcriscuo.alsdb.graphdb.service.UniProtMappingService
import org.apache.commons.csv.CSVRecord
/*
Add id field to provide a unique String identifier
 */
case class RnaTpmGene(
                       hugoGeneName:String, ensemblGeneId:String,
                       uniProtMapping: Optional[UniProtMapping],
                       tpm:Double, externalSampleId:String,
                       externalSubjectId:String,
                       id:String
                     ) {

}
object RnaTpmGene extends ValueTrait {


  def parseCsvRecordFunction( record:CSVRecord):RnaTpmGene = {
    val ensemblGeneId:String = record.get("EnsemblGeneId")
    // generate a composite identifier for uniqueness
    val id = ensemblGeneId +":" +record.get("ExternalSampleId")
    new RnaTpmGene(
      record.get("HugoGeneName"),
      ensemblGeneId,
      UniProtMappingService.INSTANCE.resolveUniProtMappingByEnsemblGeneId(ensemblGeneId),
      record.get("TPM").toDouble,
      record.get("ExternalSampleId"),
      record.get("ExternalSubjectId"),
      id
    )
  }
  val columnHeadings:Array[String] = Array("HugoGeneName","EnsemblGeneId", "TPM", "ExternalSampleId","ExternalSubjectId")

}
