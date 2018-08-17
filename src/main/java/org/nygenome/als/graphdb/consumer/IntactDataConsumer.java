package org.nygenome.als.graphdb.consumer;


import org.nygenome.als.graphdb.model.PsiMitab;
import org.nygenome.als.graphdb.util.TsvRecordStreamSupplier;
import org.nygenome.als.graphdb.util.Utils;
import scala.Tuple2;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class IntactDataConsumer extends GraphDataConsumer implements Consumer<Path> {

  private static final String HEADINGS_FILE_NAME = "heading_intact.txt";

  private Predicate<PsiMitab> selfInteractionPredicate = (ppi) ->
      ! ppi.getIntearctorAId().equals(ppi.getInteractorBId());


  private Function<Path,Path> resolveHeadingFilePathFunction = (dirPath) ->
    Paths.get(dirPath.toString(),HEADINGS_FILE_NAME);


  /*
  Process the human intact data. The file provided from IntAct is excessively
  large. Manually split the large file using the split command and process all
  the individual components
  The Path argument must be a directory
   */
  @Override
  public void accept(Path path) {

    new TsvRecordStreamSupplier(path).get()
        .map(PsiMitab.parseCsvRecordFunction)
        .filter(selfInteractionPredicate)
        .forEach(proteinInteractionConsumer);
  }

  private Consumer<Tuple2<String,String>> novelProteinNodesConsumer = (tuple) -> {
    if (!proteintMap.containsKey(tuple._1())) {
      createProteinNode(strNoInfo, tuple._1(), strNoInfo,
          strNoInfo, strNoInfo, strNoInfo);
    }
    if (!proteintMap.containsKey(tuple._2())) {
      createProteinNode(strNoInfo,tuple._2(), strNoInfo,
          strNoInfo, strNoInfo, strNoInfo);
    }
  };

  private Consumer<PsiMitab> proteinInteractionConsumer = (ppi) -> {
    Tuple2<String,String> abTuple = new Tuple2<>(ppi.getIntearctorAId(),ppi.getInteractorBId());
    Tuple2<String,String> baTuple = new Tuple2<>(ppi.getInteractorBId(), ppi.getIntearctorAId());
    if ((!vPPIMap.containsKey(abTuple))
        && (!vPPIMap.containsKey(baTuple))) {
      novelProteinNodesConsumer.accept(abTuple);  // register protein node if novel
      vPPIMap.put(
          abTuple,
          proteintMap
              .get(ppi.getIntearctorAId())
              .createRelationshipTo(
                  proteintMap.get(ppi.getInteractorBId()),
                  Utils.convertStringListToRelType (ppi.getInteractionTypeList())));
      // add interaction properties
      // detetcion methods
      vPPIMap.get(abTuple).setProperty("Interaction_method_detection",
          ppi.reduceListToStringFunction.apply(ppi.getDetectionMethodList()));
      vPPIMap.get(abTuple).setProperty("Reference",
          ppi.reduceListToStringFunction.apply(ppi.getPublicationIdList()));
      if (ppi.getConfidenceScoreList().size() >0) {
        vPPIMap.get(abTuple).setProperty("Confidence_level",
            Double.parseDouble(ppi.getConfidenceScoreList().get(0)));
      }
    }

  };


}

