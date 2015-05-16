package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Ctasprod;
import pojo.annotations.Producto;
@Local
public interface IctasProductoDao extends IGenericDao<Ctasprod, Integer>{
	List<Producto> getProductoById(int cta);
	
	List<Ctasprod> getProductoByIdCta(int cta, int estado);

}
