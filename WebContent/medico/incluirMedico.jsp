<%@page import="br.com.consultamedica.modelo.implemento.Medico"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Médicos</title>
</head>
<body>
	<form action="../medico" method="post">

		<fieldset>

			<legend>Cadastre-se</legend>

			<label>Nome: <input type="text"
				name="<%=Medico.NM_ATR_Nome%>" required /></label><br />
			<br /> <label>Email: <input type="text"
				name="<%=Medico.NM_ATR_Email%>" required /></label><br />
			<br /> <label>Senha: <input type="password"
				name="<%=Medico.NM_ATR_Senha%>" maxlength="8" required/></label><br />
			<br /> <label>Hospital: <select
				name="<%=Medico.NM_ATR_Hospital%>">
					<option>Hospital Barao de Lucena Cordeiro</option>
					<option>Hospital Cemub Boa Viagem</option>
					<option>Hospital da Restauracao Derby</option>
					<option>Hospital Geral Pam de Areias Ipsep</option>
					<option>Hospital São Franscisco de Assis Boa Vista</option>
			</select>
			</label><br />
			<br /> <label>Crm: <input type="text"
				name="<%=Medico.NM_ATR_Crm%>" maxlength="9" required/></label><br />
			<br /> <label>Especialidade:<select
				name="<%=Medico.NM_ATR_Especialidade%>">
					<option>Clinico Geral</option>
					<option>Neurologista</option>
					<option>Pediatria</option>
			</select></label><br />
			<br />

			<button onclick="form.action: '../medico';"
				class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Cadastrar</button>

		</fieldset>

	</form>

</body>
</html>