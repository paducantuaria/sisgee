package br.cefetrj.sisgee.view;

import java.io.IOException;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Campus;
import br.cefetrj.sisgee.model.entity.Curso;
import br.cefetrj.sisgee.model.entity.Pessoa;

/**
 * Servlet para trazer os dados do Aluno por meio de requisição AJAX.
 * 
 * @author Augusto Jose
 * @since 1.0
 *
 */
@WebServlet("/BuscaAlunoServlet")
public class BuscaAlunoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String matricula = request.getParameter("matricula");
		String idAluno = "";
		String nome = "";
		String nomeCurso = "";
		String nomeCampus = "";
		
		Aluno aluno = AlunoServices.buscarAlunoByMatricula(matricula.trim());
		if(aluno != null) {
			Pessoa pessoa = aluno.getPessoa();
			Curso curso = aluno.getCurso();
			Campus campus = curso.getCampus();
			
			idAluno = Integer.toString(aluno.getIdAluno());
			nome = pessoa.getNome();
			nomeCurso = curso.getNomeCurso();
			nomeCampus = campus.getNomeCampus();
		}
		
		//JSON
		JsonObject model = Json.createObjectBuilder()
				.add("idAluno", idAluno)
				.add("nome", nome)
				.add("nomeCurso", nomeCurso)
				.add("nomeCampus", nomeCampus)
				.build();
		
		StringWriter stWriter = new StringWriter();
		JsonWriter jsonWriter = Json.createWriter(stWriter);
		jsonWriter.writeObject(model);
		jsonWriter.close();
		String jsonData = stWriter.toString();
		
		response.setContentType("application/json");
		response.getWriter().print(jsonData);
	}

}