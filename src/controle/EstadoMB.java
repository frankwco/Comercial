package controle;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import banco.DAOGenerico;
import modelo.Estado;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import util.ChamarRelatorio;

@ManagedBean
@ViewScoped
public class EstadoMB {
	private Estado estado = new Estado();
	private List<Estado> estados = new ArrayList<>();
	private DAOGenerico<Estado> dao = new DAOGenerico<>(Estado.class);
	private String nomeEstado="";

	public EstadoMB() {
		estados = dao.buscarTodos();
	}

	public void chamarRelatorio() {
		String consulta = "SELECT *from Estados";
		HashMap param = new HashMap<>();
		param.put("TITULO_RELATORIOs", "Relatório de Estados");
		ChamarRelatorio.relatorio(consulta, "relatorioEstado", 
				"relatorioEstado", param);
	}
	
	public void chamarRelatorioConexao() {
		//String consulta = "SELECT *from Estado";
		HashMap param = new HashMap<>();
		param.put("NOME_ESTADOs", nomeEstado);
		ChamarRelatorio.relatorioConexao("relatorio_estados", 
				"relatorio_estados", param);
	}

	public void inserir() {
		if (estado.getId() == null) {
			dao.salvar(estado);
		} else {
			dao.alterar(estado);
		}
		estado = new Estado();
		estados = dao.buscarTodos();
	}

	public void excluir(Long id) {
		dao.excluir(id);
		estados = dao.buscarTodos();
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
	
	

}
