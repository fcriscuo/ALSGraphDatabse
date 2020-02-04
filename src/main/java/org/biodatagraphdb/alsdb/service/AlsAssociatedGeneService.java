package org.biodatagraphdb.alsdb.service;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nonnull;

import org.biodatagraphdb.alsdb.model.AlsAssociatedGene;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Maps;

/*
Java Singleton that supports a white list of genes that
have been associated with ALS
This service class supports annotation of genetic data
imported into the database
White list supplied by ALSoD http://alsod.iop.kcl.ac.uk/misc/dataDownload.aspx#C5
 */
public enum AlsAssociatedGeneService {
  INSTANCE;

private Path alsGeneFilePath = Paths.get(
    org.biodatagraphdb.alsdb.util.FrameworkPropertyService.INSTANCE.getStringProperty("ALSOD_GENE_WHITE_LIST_FILE"));

  private final ImmutableMap<String, org.biodatagraphdb.alsdb.model.AlsAssociatedGene> alsAssociatedGeneMap =
      Suppliers.memoize(new  AlsAssociateGeneMapSupplier(alsGeneFilePath)).get();

  public Predicate<String> alsAssociatedGenePredicate = (@Nonnull String name) ->
      alsAssociatedGeneMap.containsKey(name.toUpperCase());

  public Function<String, Optional<org.biodatagraphdb.alsdb.model.AlsAssociatedGene>> resolveAlsAssociatedGeneFunction =
      (@Nonnull String name) ->
          (alsAssociatedGenePredicate.test(name))
      ? Optional.of(alsAssociatedGeneMap.get(name.toUpperCase()))
              : Optional.empty();

  class AlsAssociateGeneMapSupplier implements Supplier<ImmutableMap<String, org.biodatagraphdb.alsdb.model.AlsAssociatedGene>> {
    private final Map<String, org.biodatagraphdb.alsdb.model.AlsAssociatedGene> alsGeneMap  = Maps.mutable.empty();

    AlsAssociateGeneMapSupplier(@Nonnull Path tsvFilePath) {
      generateMap(tsvFilePath);
    }

    private void generateMap(Path tsvFilePath) {
      new org.biodatagraphdb.alsdb.util.TsvRecordStreamSupplier(tsvFilePath)
          .get()
          .map(AlsAssociatedGene.Companion::parseCSVRecord)
          .forEach(gene -> alsGeneMap.put(gene.getGeneSymbol(),gene));
    }

    @Override
    public ImmutableMap<String, org.biodatagraphdb.alsdb.model.AlsAssociatedGene> get() {
     return Maps.immutable.ofMap(this.alsGeneMap);
    }
  }

  public static void main(String[] args) {
    // test some gene names
    // BRCA1 is not an ALS gene
    // XXXXXXX is not a valid gene name
    Stream.of("CST3", "PCP4", "BRCA1", "SOD1","XXXXXXX")
        .forEach(name -> {
          AlsAssociatedGeneService.INSTANCE.resolveAlsAssociatedGeneFunction.apply(name)
              .ifPresent(System.out::println);
        });

  }

}
