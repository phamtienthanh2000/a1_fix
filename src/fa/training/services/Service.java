package fa.training.services;

import java.util.List;

public interface Service<E> {
	void create(E object);

	List<E> getAll();
}
