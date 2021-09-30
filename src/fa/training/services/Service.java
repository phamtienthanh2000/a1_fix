package fa.training.services;

import java.util.List;

/**
 * This class declares common functions 
 * @author Admin
 *
 * @param <E>
 */
public interface Service<E> {
	/**
	 * Create an object of type E
	 * @param object : the object be created
	 * @throws Exception
	 */
	void create(E object) throws Exception;

	/**
	 * get all element of type E
	 * 
	 * @return
	 */
	List<E> getAll();
}
