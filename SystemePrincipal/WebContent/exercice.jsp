<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="corenlp.*,java.util.List,java.io.File,java.util.Random,java.util.Vector,java.util.ArrayList"%>



<!DOCTYPE html>
<html style="font-size: 70%; font-family: times new roman;">
<head>
	<meta charset="UTF-8">
	<title>汉语练习</title>
	<style type="text/css">
	#reponse1,#reponse2,#reponse3,#reponse4,#reponse5 {margin-left: 10px; margin-right: 10px;	height:50px; background:#E2E1E4;}
	#reponse1 label,#reponse2 label,#reponse3 label,#reponse4 label,#reponse5 label {display:inline-table; height:20px; line-height:20px; margin:0;}
	.tokenIn1,.tokenIn2,.tokenIn3,.tokenIn4,.tokenIn5 {margin-left: 7px; margin-right: 5px; text-align:center; margin-top: 5px; border: 1px solid #7A7374; display:inline-table; width:60px; height:21px; background:#F0C9CF; color:#000000;}
	.tokenOut1,.tokenOut2,.tokenOut3,.tokenOut4,.tokenOut5 {background:#E77C8E;color:#FFFFFF;border: 1px solid #F0C9CF;}
	</style>
	<link rel="shortcut icon" href="<%request.getContextPath();%>image/panda.png" type="image/x-icon" />
</head>

<body style="color: #666666;background-color: #E2E1E4;">	

	<div class="leftzs" >
		<img src="<%request.getContextPath();%>image/line.png" id="line"  />
	</div>
	<div class="leftzs" onclick = "window.location.href = 'index.jsp'">
		<img src="<%request.getContextPath();%>image/compte.png" id="compte" />
	</div>
	<div class="leftzs" onclick = "window.location.href = 'index.jsp'">
		<img src="<%request.getContextPath();%>image/choix.png" id="choix"/>
	</div>
	<div class="leftzs" onclick = "window.location.href = 'index.jsp'">
		<img src="<%request.getContextPath();%>image/note.png" id="note">
	</div>
	
	<div class="head">
		<h1 align="center" style="color: #000000;">汉语结构练习</h1>
		<h1 align="center" style="color: #000000;">Exercice de structure chinoise</h1>		
	</div>

	<div class="main">

		<div class="question et reponse">
			<div id="intro">
				<div style="margin-top: 50px;">
					<h2 align="center" style="color: #000000;">Chaque partie contient 5 petites questions.</h2>
					<br>
					<h2 align="center" style="color: #000000;">Vous êtes prêt ?</h2>
				</div>
				
				<div id="centre">
					<input type="button",  class="ok" value="Je suis prêt !" id="commencer">
				</div>
				
				<div id="zhuangshi1">
					<img src="<%request.getContextPath();%>image/plum.png" >
				</div>
				<div id="zhuangshi2">
					<img src="<%request.getContextPath();%>image/plum2.png" >
				</div>
			</div>
			<%
				String levelFinal = request.getParameter("levelFinal");
				String livreFinal = request.getParameter("livreFinal");
	
			%>
			<% 
			File path = new File("/usr/local/apache-tomcat-9.0.41/webapps/CorpusWeb/"+levelFinal+"/");
	    	
			Texte livre = new Texte();
	    	List<File> files = livre.searchFiles(path,livreFinal);
	    	Random r = new Random();
			Vector<Integer> v = new Vector<Integer>();
			int count = 0;
			ArrayList<List> contenuList = new ArrayList();
			while(count <5){
				int number = r.nextInt(files.size());
				if(!v.contains(number)){
					v.add(number);
					count = count +1;
					File file = files.get(number);
					List contenu = livre.txt2String(file);
					contenuList.add(contenu);
				}
			}
			%>
			<%	int q = 0;
				for(List text : contenuList){
					q = q +1;
					out.println("<div hidden  id="+"'Question"+q+"'>");
					out.println("<div class='questiondiv' >");
					out.println("<h3 >Question "+q+"</h3>");
					out.println("</div>");
					out.println("</br>");
					out.println("<div class='contexte' id="+"'q"+q+"'>");
					String reference = livre.getReference(text);
					int indice = text.indexOf(reference);
					List mots = livre.motsDesordre(reference);
					text.set(indice,"La phrase à vous faire !");
					for (Object phrase : text){
						out.println(phrase+"<br>");
					}
					
					out.println("</div>");
					out.println("<div id="+"'r"+q+"'>");
					out.println("<h3 id='daan'>Veuillez mettre les mots suivants en ordre correct : </h3>");
					for (Object token : mots){
						out.println("<div class='tokenIn"+q+"' name='"+token+"'>"+token+"</div>");
					}
					
					out.println("<div id='reponse"+q+"'><h3>Votre réponse :</h3></div>");
					out.println("</div>");
					out.println("<div class='correction'>");
					out.println("<form name='valide"+q+"'>");
					out.println("<input type='hidden' id='reponseText"+q+"' name='reponseText"+q+"' value=''>");
					out.println("<input type='hidden' id='referenceText"+q+"' name='reponseText"+q+"' value='"+reference+"'>");
					out.println("<input type='button' class='ok1' value='Valide' name='submit"+q+"' id='submit"+q+"'>");
					out.println("</form>");
					out.println("<input type='button' class='ok2' id='suiv"+q+"' value='Suivant'>");
					out.println("</div>");
					out.println("</div>");
				}
			%>	
		</div>
		
		<div class="rightzs" >
			<img src="<%request.getContextPath();%>image/triangle.png" id="triangle"/>
		</div>
		<div class="rightzs" >
			<img src="<%request.getContextPath();%>image/triangle1.png" id="triangle1"/>
		</div>
		
	</div>
	<div class='tail'>
		<form action="index.jsp">
			<input hidden class="ok3"  type="button" value="Retourner" id="Retour" onclick = "window.location.href = 'index.jsp'">
		</form>
	</div>
	

	
	
	<style type="text/css">
		.correction{
			margin-left: 10px;
			margin-right: 10px;			
		}
		
		#daan{
			margin-left: 10px;
			margin-right: 10px;
		}
		
		.contexte{
			margin-left: 10px;
			margin-right: 10px;
		}
		
		.tokenIn{
			font-size: 15px;
			margin-left: 5px;
			margin-right: 5px;	
		}
		
		.ok1{
			background-color: #35333C; 
	      	border-radius: 6px; 
	     	border: 2px solid #7A7374;
	      	color: #FFFFFF; 
	      	border: 2px solid #7A7374;
	      	width: 100px;
			height: 35px;
			font-family: times new roman;
			font-size: 13px;
			float:left;
			margin-top: 10px;
			margin-left: 80px;
		}
		
		.ok2{
			background-color: #35333C; 
	      	border-radius: 6px; 
	     	border: 2px solid #7A7374;
	      	color: #FFFFFF; 
	      	border: 2px solid #7A7374;
	      	width: 100px;
			height: 35px;
			font-family: times new roman;
			font-size: 13px;
			float:right;
			margin-top: 10px;
			margin-right: 80px;
		}
		
		.ok3{
			background-color: #35333C; 
	      	border-radius: 6px; 
	     	border: 2px solid #7A7374;
	      	color: #FFFFFF; 
	      	border: 2px solid #7A7374;
	      	width: 100px;
			height: 35px;
			font-family: times new roman;
			font-size: 13px;
			float:right;
			margin-right: 80px;
		}
		
		#linephrase{
			font-style:italic; 
			text-align:center;
			text-decoration:underline;
		}
		
		.questiondiv{
			background-color: #35333C; 
	     	border: 2px solid #7A7374;
	      	color: #FFFFFF; 
	      	border: 2px solid #7A7374;
	      	width: 570px;
			height: 30px;
			font-family: times new roman;
			font-size: 12px;
			text-align:centre;
			align:centre;
		}
		
		.rightzs{
			float: right;
			position: absolute;
		}
		
		#triangle1{
			margin-top: 10px;
			margin-left: 660px;
			width:270px;
			height:270px;
		}
		
		#triangle{
			margin-top: 25px;
			margin-left: 635px;
			width:250px;
			height:250px;
		}
		
		#line{
			width:200px;
			height:250px;
		}
		#compte{
			margin-top: 40px;
			margin-left: 110px;
			width:30px;
			height:30px;
		}
		#choix{
			margin-top: 100px;
			margin-left: 110px;
			width:30px;
			height:30px;
		}
		#note{
			margin-top: 160px;
			margin-left: 110px;
			width:30px;
			height:30px;
		}
		
		.leftzs{
			position: absolute;
		}
		
		#zhuangshi1{
			margin-top: 5px;
			margin-left: 30px;
			width:0px;
			height:0px;
		}
		
		#zhuangshi2{
			margin-top: 0px;
			margin-left: 320px;
			width:0px;
			height:0px;
		}
		
		#centre{
			text-align:center;
			margin-top: 50px;
		}
		
		#zhuangshi{
			position:absolute;
			margin-top: 0px;
			margin-left: 1130px;
			width:0px;
			height:0px;
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
		
		.main{
			margin-left: auto;
			margin-right: auto;
			color:#000000;
			width: 570px;
			height:465px;
			margin-top: 35px;
			text-align:left;
			background-color: #FFFFFF; 
			border: 2px solid #E2E1E4;
			overflow-x: hidden;
        	overflow-y: auto;
		}
		
		
	</style>
</body>
</html>

<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	var score = 0;
	$(function(){
		$("#commencer").click(function(){
			$("#Question1").show();
			$("#intro").hide();
			$("#triangle1").hide();
			$("#triangle").hide();
		})
		var reponse1 = "";
		$(".tokenIn1").click(function(){
			$(this).toggleClass("tokenOut1");
			var css = $(this).attr("class");
			var name = $(this).attr("name");
			if(css != "tokenIn1"){
				$("#reponse1").append($("<label name='" + name + "'>" + name + "</label>"));
				reponse1 = reponse1+name;
			}else{
				$("#reponse1").find("label[name='" + name + "']").remove();
				reponse1 = reponse1.replace(name,"");
			}
			document.getElementById("reponseText1").value = reponse1;
		})
		$("#submit1").click(function(){
			var reponseText = $("#reponseText1").val();
			var referenceText = $("#referenceText1").val();
			$.ajax({
				type :"post",
				url : "verification.jsp",
				data: "reponseText=" + reponseText + "&referenceText=" + referenceText,
				success:function(resultat){
					alert(resultat);
					if($.trim(resultat) =="Bravo!"){
						score = score+1;
					}
				},
				error:function(){
					alert("not ok");
				}
			}); 
			return false;
		})
		
		$("#suiv1").click(function(){
			$("#Question1").hide();
			$("#Question2").show();
		})
		var reponse2 = "";
		$(".tokenIn2").click(function(){
			$(this).toggleClass("tokenOut2");
			var css = $(this).attr("class");
			var name = $(this).attr("name");
			if(css != "tokenIn2"){
				$("#reponse2").append($("<label name='" + name + "'>" + name + "</label>"));
				reponse2 = reponse2+name;
			}else{
				$("#reponse2").find("label[name='" + name + "']").remove();
				reponse2 = reponse2.replace(name,"");
			}
			document.getElementById("reponseText2").value = reponse2;

		})
		$("#submit2").click(function(){
			var reponseText = $("#reponseText2").val();
			var referenceText = $("#referenceText2").val();
			$.ajax({
				type :"post",
				url : "verification.jsp",
				data: "reponseText=" + reponseText + "&referenceText=" + referenceText,
				success:function(resultat){
					alert(resultat);
					if($.trim(resultat) =="Bravo!"){
						score = score+1;
					}
				},
				error:function(){
					alert("not ok");
				}
			}); 
			return false;
		})
		
		$("#suiv2").click(function(){
			$("#Question2").hide();
			$("#Question3").show();
		})
		var reponse3 = "";
		$(".tokenIn3").click(function(){
			$(this).toggleClass("tokenOut3");
			var css = $(this).attr("class");
			var name = $(this).attr("name");
			if(css != "tokenIn3"){
				$("#reponse3").append($("<label name='" + name + "'>" + name + "</label>"));
				reponse3 = reponse3+name;
			}else{
				$("#reponse3").find("label[name='" + name + "']").remove();
				reponse3 = reponse3.replace(name,"");
			}
			document.getElementById("reponseText1").value = reponse3;
		})
		
		$("#submit3").click(function(){
			var reponseText = $("#reponseText3").val();
			var referenceText = $("#referenceText3").val();
			$.ajax({
				type :"post",
				url : "verification.jsp",
				data: "reponseText=" + reponseText + "&referenceText=" + referenceText,
				success:function(resultat){
					alert(resultat);
					if($.trim(resultat) =="Bravo!"){
						score = score+1;
					}
				},
				error:function(){
					alert("not ok");
				}
			}); 
			return false;
		})
		
		$("#suiv3").click(function(){
			$("#Question3").hide();
			$("#Question4").show();
		})
		var reponse4 = "";
		$(".tokenIn4").click(function(){
			$(this).toggleClass("tokenOut4");
			var css = $(this).attr("class");
			var name = $(this).attr("name");
			if(css != "tokenIn4"){
				$("#reponse4").append($("<label name='" + name + "'>" + name + "</label>"));
				reponse4 = reponse4+name;

			}else{
				$("#reponse4").find("label[name='" + name + "']").remove();
				reponse4 = reponse4.replace(name,"");
			}
			document.getElementById("reponseText4").value = reponse4;
		})
		$("#submit4").click(function(){
			var reponseText = $("#reponseText4").val();
			var referenceText = $("#referenceText4").val();
			$.ajax({
				type :"post",
				url : "verification.jsp",
				data: "reponseText=" + reponseText + "&referenceText=" + referenceText,
				success:function(resultat){
					alert(resultat);
					if($.trim(resultat) =="Bravo!"){
						score = score+1;
					}
				},
				error:function(){
					alert("not ok");
				}
			}); 
			return false;
		})
		
		$("#suiv4").click(function(){
			$("#Question4").hide();
			$("#Question5").show();
		})
		var reponse5 = "";
		$(".tokenIn5").click(function(){
			$(this).toggleClass("tokenOut5");
			var css = $(this).attr("class");
			var name = $(this).attr("name");
			if(css != "tokenIn5"){
				$("#reponse5").append($("<label name='" + name + "'>" + name + "</label>"));
				reponse5 = reponse5+name;
			}else{
				$("#reponse5").find("label[name='" + name + "']").remove();
				reponse5 = reponse5.replace(name,"");
			}
			document.getElementById("reponseText5").value = reponse5;
		})
		$("#submit5").click(function(){
			var reponseText = $("#reponseText5").val();
			var referenceText = $("#referenceText5").val();
			$.ajax({
				type :"post",
				url : "verification.jsp",
				data: "reponseText=" + reponseText + "&referenceText=" + referenceText,
				success:function(resultat){
					alert(resultat);
					if($.trim(resultat) =="Bravo!"){
						score +=1;
					}
				},
				error:function(){
					alert("not ok");
				}
			}); 
			return false;
		})
		
		$("#suiv5").click(function(){
			alert("Votre score final est de "+score+".");
		})
		
		$("#commencer").click(function(){
			$("#Retour").show();
		})

		
	})
</script>