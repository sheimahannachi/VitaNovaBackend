package com.example.vitanovabackend.Service;

import com.example.vitanovabackend.DAO.Entities.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductIService {
    public Product addProduct(Product product, MultipartFile file);
    public Product updateProduct(Long idPr, Product updatedProduct, MultipartFile newImage) ;
    public List<Product > getProducts();
    public ResponseEntity<String> archiverProduct(Long idPr);
    public Product getProductById(Long idPr);
    public List<Product> searchProductsByName(String searchTerm);
  //  public  List<Product> findByCategoriePrAndPricePrAndStatusPr(String categoriePr, float pricePr, String statusPr);
  //List<Product> filterProductsByCategoryAndStatusAndPriceRange(String categoriePr, String statusPr, float pricePr);
  public List<Product> filterProducts(String categoriePr, Float pricePr);
}