import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import shi.quan.antlr4test.parsers.QueryBaseListener;
import shi.quan.antlr4test.parsers.QueryLexer;
import shi.quan.antlr4test.parsers.QueryListener;
import shi.quan.antlr4test.parsers.QueryParser;

public class MAIN {
    public static void main(String[] args) {
        QueryLexer queryLexer = new QueryLexer(CharStreams.fromString("SELECT F1, F2, F3, F4 FROM VIEW1 WHERE F1 < 8 AND F2 = 'abcd' ORDER BY F3, F4 DSC;"));

        CommonTokenStream tokenStream = new CommonTokenStream(queryLexer);

        QueryParser parser = new QueryParser(tokenStream);

        QueryParser.QueriesContext context = parser.queries();

        ParseTreeWalker walker = new ParseTreeWalker();

        QueryListener listener = new QueryBaseListener() {
            @Override
            public void enterQuery_field(QueryParser.Query_fieldContext ctx) {
                System.out.println("[enterQuery_field] " + ctx.getText());
            }

            @Override
            public void enterView(QueryParser.ViewContext ctx) {
                System.out.println("[enterView] " + ctx.getText());
            }

            @Override
            public void enterWhere_clause(QueryParser.Where_clauseContext ctx) {
                System.out.println("[enterWhere_clause] " + ctx.getChildCount());
            }

            @Override
            public void enterSub_expression(QueryParser.Sub_expressionContext ctx) {
                System.out.println("[enterSub_expression] " + ctx.getChildCount());

                for(int i = 0; i < ctx.getChildCount(); ++i) {
                    System.out.println("\tchild : " + ctx.getChild(i).getText());
                }
            }

            @Override
            public void enterOrder_by_clause(QueryParser.Order_by_clauseContext ctx) {
                System.out.println("[enterOrder_by_clause] " + ctx.getChildCount());
            }

            @Override
            public void enterOrder_by_field(QueryParser.Order_by_fieldContext ctx) {
                System.out.println("[enterOrder_by_field] " + ctx.getChildCount());

                for(int i = 0; i < ctx.getChildCount(); ++i) {
                    System.out.println("\tchild : " + ctx.getChild(i).getText());
                }
            }
        };

        walker.walk(listener, context);
    }
}
