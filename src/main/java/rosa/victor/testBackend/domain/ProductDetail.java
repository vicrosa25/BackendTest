package rosa.victor.testBackend.domain;


import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class ProductDetail {

  private String id;
  private String name;
  private double price;
  private boolean availability;

}
