/**
 * 
 */
package cams7.apps.desk.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import cams7.apps.desk.action.AbstractAction;
import cams7.apps.desk.component.Paginacao;
import cams7.apps.desk.event.SearchEvent;
import cams7.apps.desk.jpa.repository.BaseRepository;
import cams7.apps.desk.ui.SearchView;
import cams7.apps.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public abstract class BaseSearchCtrl<V extends SearchView, D extends BaseRepository<E, ?>, E extends BaseEntity<?>>
		extends BaseCtrl<V, D, E> {

	@Autowired(required = true)
	private Paginacao paginacao;

	public BaseSearchCtrl() {
		super();
	}

	public BaseSearchCtrl(BaseListCtrl<?, D, E> listCtrl) {
		super(listCtrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#init()
	 */
	@PostConstruct
	@Override
	protected void initCtrl() {
		super.initCtrl();

		initView();

		getView().addWindowListener(this);

		registerAction(getView().getBtnBuscar(), new AbstractAction() {
			private List<E> list;

			@Override
			public void action() {
				// System.out.println(paginacao);
				if (getView().getBuscar().length() > 0) {
					String searchText = getView().getBuscar().concat("%");

					long totalRegistros = getTotalRegistros(searchText);
					paginacao.iniciaPaginacao(totalRegistros, false);

					list = getEntities(searchText);

					paginacao.setSearch(searchText);
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#posAction()
			 */
			@Override
			public void posAction() {
				cleanUp();
				@SuppressWarnings("unchecked")
				SearchEvent event = new SearchEvent((List<BaseEntity<?>>) list);
				fireEvent(event);
				list = null;
			}
		});
	}

	public void show() {
		getView().setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.listener.BaseListener#cleanUp()
	 */
	@Override
	protected void cleanUp() {
		getView().setVisible(false);
		getView().resetForm();

		super.cleanUp();
	}

	public long getTotalRegistros(String searchText) {
		long totalRegistros;
		if (searchText != null && !searchText.isEmpty())
			totalRegistros = getRepository().countBySearch(searchText);
		else
			totalRegistros = getRepository().countAll();
		return totalRegistros;
	}

	public List<E> getEntities(String searchText) {
		// Create & Initialize a Sort Object
		Sort sort = new Sort(Direction.DESC, "id");
		// Create & Initialize a Page Object

		final int PAGINA_ATUAL = paginacao.getAtualPagina();
		final byte REGISTROS_POR_PAGINA = paginacao.getRegistrosPorPagina();
		Pageable pageable = new PageRequest(PAGINA_ATUAL, REGISTROS_POR_PAGINA,
				sort);

		Page<E> page;
		if (searchText != null && !searchText.isEmpty())
			page = getRepository().findBySearch(searchText, pageable);
		else
			page = getRepository().findAllOrdered(pageable);

		List<E> entities = page.getContent();
		return entities;
	}
}
