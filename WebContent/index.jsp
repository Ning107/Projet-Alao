<%@page import="javax.sound.midi.Soundbank"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %> 
<%@ page import="java.io.FileFilter" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
  
<!DOCTYPE html>
<html style="font-size: 70%; font-family: times new roman;">

<head>
<meta charset="UTF-8">
<title>汉语练习</title>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<link rel="shortcut icon" href="<%request.getContextPath();%>image/panda.png" type="image/x-icon" />
</head>

<body style="color: #666666;background-color: #E2E1E4;">
	
	<div>
		<h1 align="center" style="color: #000000;">汉语结构练习</h1>
		<h1 align="center" style="color: #000000;">Exercice de structure chinoise</h1>
		<%!StringBuffer result = new StringBuffer();%>
    </div>
    
    <div class="compte">
    	<img src="<%request.getContextPath();%>image/compte.png" class="iconemenu" >
    	<div class="title">
			<h2 style="text-align:center;">Le statut de compte: Visiteur</h2>
			<h3 style="font-style:italic; text-align:center;text-decoration:underline">Connectez-vous à mon compte</h3>
		</div>
    </div>
    
    <div class="choisir">
    	<img src="<%request.getContextPath();%>image/choix.png" class="iconemenu" >
   		<div class="title">
			<h2 style="text-align:center;">Choisir le niveau et le livre d'exercice</h2>
		</div>
    
		<div>
			<%String level = request.getParameter("test"); %>
	   		<label for="level">Choisir le niveau : </label>
				<select id="level" >
					<option>初级</option>
					<option>中级</option>
				</select>
				<input type="button" value="Valide" id="valideLevel">
				<p>
				<%
				if (level != null){
				out.println("Niveau："+level);
				out.println("<input type='hidden' value="+level+" id='levelFinal'>");} %></p>
				<form name="frmlevel" action="index.jsp" id="frmlevelId" mothed="post"/>
					<input id="test" type="hidden" name="test">
				</form>

		</div>
		
		<div>			
			<label id="titleLevel" for="livre">
			<%
			if (level != null){
				out.println("Livre : "+level);
			} %>
			</label>
			<%
			if(level != null){
				//将Corpus放在下载的包的webapps这个包下面
				File dir = new File("/usr/local/apache-tomcat-9.0.41/webapps/CorpusWeb/"+level+"/");
			 	String[] fileNames =dir.list();
				result = new StringBuffer();
				ArrayList<String> livres = new ArrayList<String>();
				for(String str : fileNames){
					boolean shouldAdd = true;
					if(str != null){
						int i = str.indexOf("》");
						String name = str.substring(1, i);
						if (livres.contains(name)){
							shouldAdd = false;
						}
						if(shouldAdd){
							livres.add(name);
						}
					}
				}
				out.println("<form action='exercice.jsp' action='post' onsubmit='return check()'>");
				out.println("<select id='livre' name='livreFinal'>");
				for (String livre : livres){
					out.println("<option>" + livre + "</option>");
				}
				out.println("</select>");
				if (level != null){
					out.println("<input type='hidden' value="+level+" id='levelFinal' name='levelFinal'>");}
				out.println("<input type='submit' value='Commencer' id='commencer'>");
				out.println("</form>");
			}	
			%>
		</div>
		
	</div>
	
	
	<div class="note">
    	<img src="<%request.getContextPath();%>image/note.png" class="iconemenusp" >
    	<div class="title">
			<h2 style="text-align:center;">Voir mes exercices notés</h2>
		</div>
    </div>
    
	<div id="zhuangshi">
		<img src="<%request.getContextPath();%>image/plum2.png" >
	</div>

	<style type="text/css">
		#zhuangshi{
			
			margin-top: 310px;
			margin-left: 1130px;

			width:0px;
			height:0px;
		}
		
		
		.title{
			margin-top: 40px;
		}
		
		.ok{
			background-color: #35333C; 
	      	border-radius: 6px; 
	     	border: 2px solid #7A7374;
	      	color: #FFFFFF; 
	      	border: 2px solid #7A7374;
	      	width: 120px;
			height: 35px;
			font-family: times new roman;
			font-size: 14px;
		}
		
		.iconemenusp{
			margin-top: 30px;
			width: 140px;
			height: 140px;
		}
		
        .iconemenu{
			margin-top: 80px;
			width: 130px;
			height: 130px;

		}
		
        .compte{
			float: left;
			width: 380px;
			height: 50px;
			margin-top: 0px;
			text-align:center;
		}
		
		.choisir{
			margin-left: auto;
			margin-right: auto;
			width: 380px;
			height: 50px;
			margin-top: 0px;
			text-align:center;
		}
		
		.note{
			float: right;
			width: 380px;
			height: 50px;
			margin-top: 0px;
			text-align:center;
		}
    </style>

	<script type="application/javascript">
		
		$(document).ready(function(){
			$("#valideLevel").click(function(){
				var level = $("#level option:selected").text()
				//$("#titleLevel").text($("#level option:selected").text()+"课本：");
				document.getElementById("test").value = level;
				var frm = document.getElementById("frmlevelId");
				frm.submit();
				
			});
		});
	</script>

</body>

</html>