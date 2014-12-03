package cams7.desk.component;

import org.springframework.stereotype.Component;

@Component
public class Paginacao {

	public static final Byte REGISTROS_POR_PAGINA[] = new Byte[] { 3, 5, 10,
			15, 30, 50 };

	public static final Byte REGISTROS_POR_PAGINA_DEFAULT = 3;
	public static final Short ATUAL_PAGINA_DEFAULT = 0;

	public Byte registrosPorPagina;
	private Short atualPagina;
	private Long totalRegistros;

	private String search;

	public Paginacao() {
		super();
	}

	@Override
	public String toString() {
		return String
				.format("Registros por pagina: %d, Pagina atual: %d, Total de registros: %d",
						registrosPorPagina, atualPagina, totalRegistros);
	}

	public Short getTotalPaginas() {
		Short totalPaginas;
		if (getTotalRegistros() % getRegistrosPorPagina() == 0)
			totalPaginas = (short) (getTotalRegistros() / getRegistrosPorPagina());
		else
			totalPaginas = (short) ((getTotalRegistros() / getRegistrosPorPagina()) + 1);
		return totalPaginas;
	}

	public void iniciaPaginacao(Long totalRegistros,
			boolean inicializaRegistrosPorPagina) {
		this.totalRegistros = totalRegistros;

		if (inicializaRegistrosPorPagina)
			registrosPorPagina = REGISTROS_POR_PAGINA_DEFAULT;

		setPrimeiraPagina();
	}

	private boolean atualizaPaginacao() {
		boolean atualiza = (getAtualPagina().compareTo(
				(short) (getTotalPaginas() - 1)) > 0);
		if (atualiza)
			setAnteriorPagina();
		return atualiza;
	}

	public void atualizaPaginacao(Long totalRegistros) {
		this.totalRegistros = totalRegistros;

		atualizaPaginacao();
	}

	public void setPrimeiraPagina() {
		this.atualPagina = ATUAL_PAGINA_DEFAULT;
	}

	private Short getAnteriorPagina() {
		Short paginaAnterior = getAtualPagina();
		if (getAtualPagina().compareTo(ATUAL_PAGINA_DEFAULT) > 0)
			paginaAnterior = (short) (getAtualPagina() - 1);
		return paginaAnterior;
	}

	public void setAnteriorPagina() {
		atualPagina = getAnteriorPagina();
	}

	private Short getProximaPagina() {
		Short paginaAnterior = getAtualPagina();
		if (getAtualPagina().compareTo(getTotalPaginas()) < 0)
			paginaAnterior = (short) (getAtualPagina() + 1);
		return paginaAnterior;
	}

	public void setProximaPagina() {
		atualPagina = getProximaPagina();
	}

	public void setUltimaPagina() {
		atualPagina = (short) (getTotalPaginas() - 1);
	}

	public boolean existeAnteriorPagina() {
		boolean existeAnteriorPagina = getAtualPagina().compareTo(
				ATUAL_PAGINA_DEFAULT) > 0;
		return existeAnteriorPagina;
	}

	public boolean existeProximaPagina() {
		boolean existeProximaPagina = getAtualPagina().compareTo(
				(short) (getTotalPaginas() - 1)) < 0;
		return existeProximaPagina;
	}

	public Short getAtualPagina() {
		return atualPagina;
	}

	public boolean setAtualPagina(Short atualPagina) {
		if (getAtualPagina().equals(--atualPagina))
			return false;

		if (atualPagina.compareTo(ATUAL_PAGINA_DEFAULT) < 0)
			return false;

		if (atualPagina.compareTo(getTotalPaginas()) >= 0)
			return false;

		this.atualPagina = atualPagina;

		return true;
	}

	public Byte getRegistrosPorPagina() {
		return registrosPorPagina;
	}

	public boolean setRegistrosPorPagina(Byte registrosPorPagina) {
		if (getRegistrosPorPagina().equals(registrosPorPagina))
			return false;

		this.registrosPorPagina = registrosPorPagina;

		while (atualizaPaginacao())
			;

		return true;
	}

	public Long getTotalRegistros() {
		return totalRegistros;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
