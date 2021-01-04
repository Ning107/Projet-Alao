<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="corenlp.*"%>
<%
	String reponse = request.getParameter("reponseText");
	String reference = request.getParameter("referenceText");
	//out.println("Votre réponse ："+reponse);
	//out.println("La reference ："+reference);
	Texte text =  new Texte();
	String fin = text.verifier(reference, reponse);
	out.println(text.verifier(reference, reponse));
%>