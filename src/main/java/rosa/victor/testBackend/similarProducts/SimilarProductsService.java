package rosa.victor.testBackend.similarProducts;

import java.io.NotActiveException;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rosa.victor.testBackend.domain.ProductDetail;

@Service
public class SimilarProductsService {

  private WebClient webClient;

  public SimilarProductsService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Flux<Integer> getSimilarProductsIds(String productId) {
    return webClient.get()
        .uri("/product/{productId}/similarids", productId)
        .retrieve()
        .bodyToFlux(Integer.class);
  }

  public Mono<ProductDetail> getProductDetail(Integer productId) {
    return webClient.get()
        .uri("/product/{productId}", productId)
        .retrieve()
        .bodyToMono(ProductDetail.class);
  }

  public Mono<List<ProductDetail>> getSimilarProductsDetail(String productId) {
    Flux<Integer> productsIds = getSimilarProductsIds(productId);
    return productsIds.flatMap(this::getProductDetail)
        .switchIfEmpty(Mono.error(new NotActiveException()))
        .collectList();
  }

}
