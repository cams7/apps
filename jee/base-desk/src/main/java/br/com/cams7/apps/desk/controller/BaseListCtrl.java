/**
 * 
 */
package br.com.cams7.apps.desk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.persistence.metamodel.Attribute;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cams7.apps.desk.action.AbstractAction;
import br.com.cams7.apps.desk.component.Paginacao;
import br.com.cams7.apps.desk.event.AbstractEventListener;
import br.com.cams7.apps.desk.event.DeleteEvent;
import br.com.cams7.apps.desk.event.EditEvent;
import br.com.cams7.apps.desk.event.ListEvent;
import br.com.cams7.apps.desk.event.SearchEvent;
import br.com.cams7.apps.desk.jpa.repository.BaseRepository;
import br.com.cams7.apps.desk.listener.BaseListener;
import br.com.cams7.apps.desk.ui.ListView;
import br.com.cams7.apps.jpa.domain.BaseEntity;
import br.com.cams7.apps.jpa.domain.util.EntityAttribute;
import br.com.cams7.apps.util.ApplicationUtil;

/**
 * @author cesar
 *
 */
public abstract class BaseListCtrl<V extends ListView, D extends BaseRepository<E, ?>, E extends BaseEntity<?>>
		extends BaseCtrl<V, D, E> {

	@Autowired
	private Paginacao paginacao;

	public BaseListCtrl() {
		super();
	}

	protected BaseListCtrl(BaseListener parentListener) {
		super(parentListener);
	}

	/**
	 * Método executado pelo <code>Spring</code>, depois de criar a instância de
	 * <code>BaseListCtrl</code>.
	 * 
	 * <p>
	 * Faz o registro das ações e tratadores de eventos.
	 * </p>
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see cams7.desk.controller.BaseCtrl#init()
	 */
	@PostConstruct
	@Override
	protected void initCtrl() {
		super.initCtrl();

		Map<String, EntityAttribute> entityAttributes = ApplicationUtil
				.getEntityFields(getEntityType());

		setEntityAttributes(entityAttributes);
		getEditCtrl().setEntityAttributes(entityAttributes);
		getSearchCtrl().setEntityAttributes(entityAttributes);

		initView();

		getView().addWindowListener(this);

		registerAction(getView().getBtnNovo(), new AbstractAction() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#action()
			 */
			public void action() {
				getEditCtrl().show();
			}
		});

		registerAction(getView().getBtnAtualizar(), new AbstractAction() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#action()
			 */
			public void action() {
				ListEvent event = new ListEvent();
				fireEvent(event);
			}
		});

		registerAction(getView().getBtnBuscar(), new AbstractAction() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#action()
			 */
			public void action() {
				getSearchCtrl().show();
			}
		});

		getView().getTblEntities().addMouseListener(new MouseAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent
			 * )
			 */
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					@SuppressWarnings("unchecked")
					E entity = (E) getView().getTblEntities()
							.getEntitySelected();
					if (entity != null) {
						getEditCtrl().show(entity);
					}
				}
			}
		});

		registerEventListener(EditEvent.class,
				new AbstractEventListener<EditEvent>() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * cams7.desk.event.AbstractEventListener#handleEvent(cams7
					 * .desk.event.AbstractEvent)
					 */
					public void handleEvent(EditEvent event) {
						SwingUtilities.invokeLater(new Runnable() {
							/*
							 * (non-Javadoc)
							 * 
							 * @see java.lang.Runnable#run()
							 */
							public void run() {
								atualizaPaginacao();
							}
						});
					}
				});

		registerEventListener(DeleteEvent.class,
				new AbstractEventListener<DeleteEvent>() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * cams7.desk.event.AbstractEventListener#handleEvent(cams7
					 * .desk.event.AbstractEvent)
					 */
					public void handleEvent(DeleteEvent event) {
						SwingUtilities.invokeLater(new Runnable() {
							/*
							 * (non-Javadoc)
							 * 
							 * @see java.lang.Runnable#run()
							 */
							public void run() {
								atualizaPaginacao();
							}
						});
					}
				});

		registerEventListener(ListEvent.class,
				new AbstractEventListener<ListEvent>() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * cams7.desk.event.AbstractEventListener#handleEvent(cams7
					 * .desk.event.AbstractEvent)
					 */
					public void handleEvent(ListEvent event) {
						SwingUtilities.invokeLater(new Runnable() {
							/*
							 * (non-Javadoc)
							 * 
							 * @see java.lang.Runnable#run()
							 */
							public void run() {
								paginacao.setSearch(null);
								iniciaPaginacao(false);
							}
						});
					}
				});

		registerEventListener(SearchEvent.class,
				new AbstractEventListener<SearchEvent>() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * cams7.desk.event.AbstractEventListener#handleEvent(cams7
					 * .desk.event.AbstractEvent)
					 */
					public void handleEvent(final SearchEvent event) {
						SwingUtilities.invokeLater(new Runnable() {
							/*
							 * (non-Javadoc)
							 * 
							 * @see java.lang.Runnable#run()
							 */
							public void run() {
								List<BaseEntity<?>> list = event.getTarget();
								if (list != null) {
									verificaPagina();
									montaStatusPaginas();
									getView().refreshTable(list);
								}
							}
						});
					}
				});

		registerAction(getView().getBtnPrimeiraPagina(), new AbstractAction() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#action()
			 */
			public void action() {
				primeiraPagina();
			}
		});

		registerAction(getView().getBtnAnterior(), new AbstractAction() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#action()
			 */
			public void action() {
				anteriorPagina();
			}
		});

		registerAction(getView().getBtnProxima(), new AbstractAction() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#action()
			 */
			public void action() {
				proximaPagina();
			}
		});

		registerAction(getView().getBtnUltimaPagina(), new AbstractAction() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see cams7.desk.action.AbstractAction#action()
			 */
			public void action() {
				ultimaPagina();
			}
		});

		getView().getTxtAtualPagina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaAtualPagina();
			}
		});

		getView().getCmbRegistrosPorPagina().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						atualizaRegistrosPorPagina();
					}
				});

		getView().setVisible(false);

	}

	protected abstract BaseEditCtrl<?, D, E> getEditCtrl();

	protected abstract BaseSearchCtrl<?, D, E> getSearchCtrl();

	public void show() {
		getView().setTitle(getBundleMessage().getListViewTitle());

		paginacao.setSearch(null);
		iniciaPaginacao(true);
		getView().setVisible(true);
	}

	private long getTotalRegistros() {
		long totalRegistros = getSearchCtrl().getTotalRegistros(
				paginacao.getSearch());
		return totalRegistros;
	}

	@SuppressWarnings("unchecked")
	private void refreshTable() {
		List<BaseEntity<?>> entities = (List<BaseEntity<?>>) getSearchCtrl()
				.getEntities(paginacao.getSearch());
		getView().refreshTable(entities);
	}

	private void montaStatusPaginas() {
		getView().getTxtAtualPagina().setValue(
				(short) (paginacao.getAtualPagina() + 1));
		getView().getLblTotalPaginas().setText(
				paginacao.getTotalPaginas() > 0 ? paginacao.getTotalPaginas()
						.toString() : "1");

	}

	private void verificaPagina() {
		boolean anteriorPagina = paginacao.existeAnteriorPagina();

		getView().getBtnPrimeiraPagina().setEnabled(anteriorPagina);
		getView().getBtnAnterior().setEnabled(anteriorPagina);

		boolean proximaPagina = paginacao.existeProximaPagina();

		getView().getBtnProxima().setEnabled(proximaPagina);
		getView().getBtnUltimaPagina().setEnabled(proximaPagina);
	}

	private void atualizaPagina() {
		verificaPagina();
		montaStatusPaginas();
		refreshTable();
	}

	private void iniciaPaginacao(boolean inicializaRegistrosPorPagina) {
		paginacao.iniciaPaginacao(getTotalRegistros(),
				inicializaRegistrosPorPagina);
		atualizaPagina();
	}

	private void atualizaPaginacao() {
		paginacao.atualizaPaginacao(getTotalRegistros());
		atualizaPagina();
	}

	private void primeiraPagina() {
		paginacao.setPrimeiraPagina();
		atualizaPagina();
	}

	private void anteriorPagina() {
		paginacao.setAnteriorPagina();
		atualizaPagina();
	}

	private void atualizaAtualPagina() {
		boolean atualiza = paginacao.setAtualPagina(((Short) getView()
				.getTxtAtualPagina().getValue()));
		if (atualiza)
			atualizaPagina();
	}

	private void proximaPagina() {
		paginacao.setProximaPagina();
		atualizaPagina();
	}

	private void ultimaPagina() {
		paginacao.setUltimaPagina();
		atualizaPagina();
	}

	private void atualizaRegistrosPorPagina() {
		boolean atualiza = paginacao.setRegistrosPorPagina((Byte) getView()
				.getCmbRegistrosPorPagina().getSelectedItem());
		if (atualiza)
			atualizaPagina();
	}

	private void mudaVisibilidadeCamposTabela(boolean visible,
			Attribute<?, ?>... attributes) {
		if (attributes.length == 0)
			return;

		byte count = 0;

		for (Attribute<?, ?> attribute : attributes) {
			String attributeName = attribute.getName();
			EntityAttribute entityAttribute = getEntityAttributes().get(
					attributeName);
			if (entityAttribute == null)
				continue;

			entityAttribute.setVisible(visible);
			count++;
		}

		if (count > 0)
			getView().refreshTable(getEntityAttributes());
	}

	protected void mudaNaoVisualizacaoCamposTabela(
			Attribute<?, ?>... attributes) {
		mudaVisibilidadeCamposTabela(false, attributes);
	}

	protected void mudaVisualizacaoCamposTabela(Attribute<?, ?>... attributes) {
		mudaVisibilidadeCamposTabela(true, attributes);
	}

	protected void mudaPrimeirosCamposTabela(Attribute<?, ?>... attributes) {
		if (attributes.length == 0)
			return;

		byte count = 0;

		final Map<String, Byte> indexes = new HashMap<String, Byte>();
		for (Attribute<?, ?> attribute : attributes) {
			String attributeName = attribute.getName();
			EntityAttribute entityAttribute = getEntityAttributes().get(
					attributeName);
			if (entityAttribute == null)
				continue;

			if (indexes.containsKey(attribute))
				continue;

			entityAttribute.setIndex(count);
			indexes.put(attributeName, count);
			count++;
		}

		if (!indexes.isEmpty()) {
			allAttributes: for (String attributeName : getEntityAttributes()
					.keySet()) {
				for (String attribute : indexes.keySet())
					if (attribute.equals(attributeName))
						continue allAttributes;

				getEntityAttributes().get(attributeName).setIndex(count);
				indexes.put(attributeName, count);
				count++;
			}

			Map<String, EntityAttribute> orderedAttributes = new TreeMap<String, EntityAttribute>(
					new Comparator<String>() {

						@Override
						public int compare(String attribute1, String attribute2) {
							return indexes.get(attribute1).compareTo(
									indexes.get(attribute2));
						}
					});

			orderedAttributes.putAll(getEntityAttributes());
			setEntityAttributes(orderedAttributes);

			getView().refreshTable(getEntityAttributes());
		}

	}
}
