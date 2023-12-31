package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String command = request.getParameter("command");
		String won = request.getParameter("won");
		String operator = request.getParameter("operator");
		
		if(command != null && command.equals("calculate")) {
			String result = calculate(Float.parseFloat(won), operator);
			out.print("<html><font size=10>변환 결과</font><br>");
			out.print("<html><font size=10>" + result + "</font><br>" );
			out.print("<a href='/webShop/calc'>환율 계산기</a>");
			return;
		}
		
		out.print("<html><title>환율 계산기</title>");
		out.print("<font size=5>환율 계산기</font><br>");
		out.print("<form name='frmCalc' method='get' action='/webShop/calc' />");
		out.print("원화: <input type='text' name='won' size=10 />");
		out.print("<select name='operator'>");
		out.print("<option value='dollar'>달러</option>");
		out.print("<option value='en'>엔화</option>");
		out.print("<option value='wian'>위안</option>");
		out.print("<option value='pound'>파운드</option>");
		out.print("<option value='euro'>유로</option>");
		out.print("</select>");
		out.print("<input type='hidden' name='command' value='calculate' />");
		out.print("<input type='submit' value='변환' />");
		out.print("</form>");
		out.print("</html>");
		out.close();
	}
	
	private static float USD_RATE =  1124.70F;
	private static float JPY_RATE = 10.113F;
	private static float CNY_RATE = 163.30F;
	private static float GNP_RATE = 1444.35F;
	private static float EUR_RATE = 1295.97F;
	
	private static String calculate(float won, String operator) {
		String result = null;
		if(operator.equals("dollar")) {
			result = String.format("%.6f", won / USD_RATE);
		}else if(operator.equals("en")) {
			result = String.format("%.6f", won / JPY_RATE);
		}else if(operator.equals("wian")) {
			result = String.format("%.6f", won / CNY_RATE);
		}else if(operator.equals("pound")) {
			result = String.format("%.6f", won / GNP_RATE);
		}else if(operator.equals("euro")) {
			result = String.format("%.6f", won / EUR_RATE);
		}
		return result;
	}

}
