<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="import_head.jspf"%>

<style type="text/css">
div.container {
	margin-bottom: 2em;
}
form {
	margin-top: 50px;
}
fieldset.form-group {
	 border:1px solid #999999;
}
fieldset legend.col-form-legend {
	font-weight: bold;
}
div.form-row {
	padding: 0px 15px;
}
</style>

<title>

	<fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.registro_termo"/>


</title>

</head>
<body>
	<%@include file="import_navbar.jspf"%>
	
	<div class="container">
		<c:if test="${ not empty msg }">
			<div class="alert alert-warning" role="alert">
				${ msg }
			</div>
		</c:if>

		<p class="tituloForm">

		<h5>		

			<fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.registro_termo"/>

		</h5>		

		</p>		
		
		<form action="FormTermoRescisaoServlet" method="post">
			
			<fieldset class="form-group dadosAluno">
				
				<%@include file="import_busca_aluno.jspf"%>
				
			</fieldset>			
			
			
			<fieldset class="form-group">
				
				<div class="form-row">
					<div class="form-group col-md-4">


						<label for="dataRescisao"><fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.data_rescisao"/></label>
						<input type="text" class="form-control ${ not empty dataTermoRescisaoMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataRescisao"  name="dataTermoRescisao" value="${ param.dataRescisao }" >
					<c:if test="${ not empty dataTermoRescisaoMsg }">
				    	<div class="invalid-feedback">${ dataTermoRescisaoMsg }</div>
		        	</c:if>
					</div>					
				</div>


			</fieldset>
		
			<button type="submit" class="btn btn-primary">Salvar</button>
			<button type="button" class="btn btn-secondary">Cancelar</button>			
		</form>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="myModalLabel"></h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
		      </div>
		    </div>
		  </div>
		</div>

	</div>
	<%@include file="import_footer.jspf"%>
	<%@include file="import_finalbodyscripts.jspf"%>
	<%@include file="import_scripts.jspf"%>
    
</body>
</html>
