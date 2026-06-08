package com.fashionstore.dao;

import com.fashionstore.model.ProductSize;
import java.util.List;

public interface ProductSizeDAO {

    boolean addProductSize(ProductSize size);

    List<ProductSize> getSizesByProductId(int productId);

    boolean updateStock(int sizeId, int stock);

    boolean deleteSize(int sizeId);
}