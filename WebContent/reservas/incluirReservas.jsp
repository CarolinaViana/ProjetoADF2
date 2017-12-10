<%@page import="br.com.consultamedica.modelo.implemento.Reserva"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Reservas</title>
</head>
<body background="">
	<form action="../reserva" method="post">

		<fieldset>

			<legend>Cadastre-se</legend>

			<label>Data: <input type="text"
				name="<%=Reserva.NM_ATR_DATA%>" /></label><br />
			<br />

			<p>
				<label>Especialidade: <select name="Especialidade:">
						<option value="1">Pediatria</option>
						<option value="2">Clinico Geral</option>
						<option value="3">Neurologista</option>
				</select></label>



			</p>
			<p>
				<label>Hospital: <select name="Hospital:">

						<option>Hospital Cemub -Boa Viagem</option>
						<option>Hospital Barao de Lucena - Cordeiro</option>
						<option>Hospital da Restauracao - Derby</option>
						<option>Hospital Geral Pam de Areias - Ipsep</option>
						<option>Hospital São Franscisco de Assis – Boa Vista</option>

				</select></label>

			</p>


			<button onclick="form.action: '../reserva';"
				class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Reservar</button>

		</fieldset>

	</form>

</body>
</html>
