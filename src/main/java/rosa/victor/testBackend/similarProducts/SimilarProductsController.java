package rosa.victor.testBackend.similarProducts;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rosa.victor.testBackend.domain.ProductDetail;

@RestController
public class SimilarProductsController {

  private SimilarProductsService similarProductsService;

  public SimilarProductsController(SimilarProductsService similarProductsService) {
    this.similarProductsService = similarProductsService;
  }

  @GetMapping("/product/{productId}/similar")
  public Mono<List<ProductDetail>> getSimilarProducts(@PathVariable("productId") String productId) {
    return similarProductsService.getSimilarProductsDetail(productId);
  }

}
