package com.prime;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/prime")
public class PrimeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Get input
            int number = Integer.parseInt(request.getParameter("number"));

            // Validation
            if (number < 0) {
                throw new IllegalArgumentException("Number cannot be negative");
            }

            // Prime logic
            boolean isPrime = true;

            if (number == 0 || number == 1) {
                isPrime = false;
            } else {
                for (int i = 2; i <= Math.sqrt(number); i++) {
                    if (number % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }

            // Response output
            out.println("<html>");
            out.println("<head><title>Prime Result</title></head>");
            out.println("<body>");
            out.println("<h2>Prime Number Result</h2>");
            out.println("<p>Entered Number: " + number + "</p>");

            if (isPrime) {
                out.println("<p style='color:green;'>It is a Prime Number</p>");
            } else {
                out.println("<p style='color:red;'>It is NOT a Prime Number</p>");
            }

            out.println("<a href='index.html'>Check Another</a>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            displayError(out, "Error Code 101: Invalid input! Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            displayError(out, "Error Code 102: " + e.getMessage());
        }
    }

    private void displayError(PrintWriter out, String message) {
        out.println("<html>");
        out.println("<head><title>Error</title></head>");
        out.println("<body>");
        out.println("<h2 style='color:red;'>Error</h2>");
        out.println("<p>" + message + "</p>");
        out.println("<a href='index.html'>Try Again</a>");
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}