package br.cefetrj.sisgee.view.termoaditivo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;

public class TermoAditivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Locale locale = ServletUtils.getLocale(request);
		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
		
		
		String vigencia = request.getParameter("vigencia");
		String endereco = request.getParameter("endereco");
		String carga = request.getParameter("cargaHoraria");
		String professor = request.getParameter("professor");
		String valorBolsa = request.getParameter("valor");
		
		
		String idAluno = request.getParameter("idAluno");
		
		
		Aluno aluno = null;
		TermoEstagio termoEstagio = null;
		
		boolean isValid = true;
		String campo = "";
		
		
			
		/**
		 * Validação do Id do Aluno, usando métodos da Classe ValidaUtils.
		 * Instanciando o objeto e pegando o TermoEstagio válido (sem data de rescisão)
		 */
		String idAlunoMsg = "";
		campo = "Aluno";
		idAlunoMsg = ValidaUtils.validaObrigatorio(campo, idAluno);
		if (idAlunoMsg.trim().isEmpty()) {
			idAlunoMsg = ValidaUtils.validaInteger(campo, idAluno);
			if (idAlunoMsg.trim().isEmpty()) {
				Integer idAlunoInt = Integer.parseInt(idAluno);
				aluno = AlunoServices.buscarAluno(new Aluno(idAlunoInt));
				if (aluno != null) {
					List<TermoEstagio> termosEstagio = aluno.getTermoEstagios();
					
					for (TermoEstagio termoEstagio2 : termosEstagio) {
						if(termoEstagio2.getDataRescisaoTermoEstagio() == null) {
							termoEstagio = termoEstagio2;
							break;
						}
					}					
					
				} else {
					idAlunoMsg = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_AlunoEscolhido");
					request.setAttribute("idAlunoMsg", idAlunoMsg);
				}

			} else {
				request.setAttribute("idAlunoMsg", idAlunoMsg);
				isValid = false;
			}
		} else {
			request.setAttribute("idAlunoMsg", idAlunoMsg);
			isValid = false;
		}

	}
}