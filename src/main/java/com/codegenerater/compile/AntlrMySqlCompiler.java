package com.codegenerater.compile;

import com.codegenerater.antlr.mysql.MySqlLexer;
import com.codegenerater.antlr.mysql.MySqlParser;
import com.codegenerater.antlr.mysql.MySqlParserBaseListener;
import com.codegenerater.common.FiledType;
import com.codegenerater.container.ManagedBean;
import com.codegenerater.model.Model;
import com.codegenerater.model.TableField;
import com.codegenerater.model.TableModel;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.Optional;

/**
 * @description: 基于ANTLR的MySQL语法树解析器
 * @author: chenshize02
 * @create: 2020-12-12 16:18
 **/
@ManagedBean
public class AntlrMySqlCompiler extends MySqlParserBaseListener {

    private TableModel tableModel;

    private TableField cursor;

    private TableField getCursor() {
        cursor = Optional.ofNullable(cursor).orElse(new TableField());
        return cursor;
    }

    private void resetCursor() {
        cursor = null;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    @Override
    public void enterTableName(MySqlParser.TableNameContext ctx) {
        tableModel.setName(extractContent(ctx));
    }

    @Override
    public void enterColumnDeclaration(MySqlParser.ColumnDeclarationContext ctx) {
        TableField currentFiled = getCursor();
        ParseTree nameNode = ctx.getChild(MySqlParser.UidContext.class, 0);
        currentFiled.setName(extractContent(nameNode));
    }

    @Override
    public void exitColumnDeclaration(MySqlParser.ColumnDeclarationContext ctx) {
        tableModel.addTableFiled(getCursor());
        resetCursor();
    }

    @Override
    public void enterColumnDefinition(MySqlParser.ColumnDefinitionContext ctx) {
        TableField currentFiled = getCursor();
        ParseTree dataTypeNode = ctx.getChild(MySqlParser.DataTypeContext.class, 0);
        ParseTree dataTypeNameNode = dataTypeNode.getChild(0);
        Optional.ofNullable(dataTypeNameNode).ifPresent(node -> currentFiled.setType(FiledType.getBySqlType(extractContent(node))));
        ParseTree commentNode = ctx.getChild(MySqlParser.CommentColumnConstraintContext.class, 0);
        Optional.ofNullable(commentNode).ifPresent(node -> currentFiled.setComment(extractContent(node.getChild(1))));
    }

    @Override
    public void enterTableOptionComment(MySqlParser.TableOptionCommentContext ctx) {
        ParseTree tbCommentNode = ctx.getChild(2);
        Optional.ofNullable(tbCommentNode).ifPresent(node -> tableModel.setDesc(extractContent(node)));
    }

    @Override
    public void enterPrimaryKeyColumnConstraint(MySqlParser.PrimaryKeyColumnConstraintContext ctx) {
        super.enterPrimaryKeyColumnConstraint(ctx);
    }

    @Override
    public void enterPrimaryKeyTableConstraint(MySqlParser.PrimaryKeyTableConstraintContext ctx) {
        MySqlParser.IndexColumnNamesContext pkContext = ctx.getChild(MySqlParser.IndexColumnNamesContext.class, 0);
        Optional.ofNullable(pkContext).ifPresent(c -> tableModel.addTablePrimaryKey(extractContent(c.getChild(MySqlParser.IndexColumnNameContext.class, 0))));
    }

    private static String extractContent(ParseTree treeNode) {
        String field = treeNode == null ? null : treeNode.getText().toLowerCase();
        if (field == null) {
            return null;
        }
        final String fieldDef = "`";
        final String commentDef = "'";
        if (field.startsWith(fieldDef) || field.startsWith(commentDef)) {
            field = field.substring(1);
        }
        if (field.endsWith(fieldDef) || field.endsWith(commentDef)) {
            field = field.substring(0, field.length() - 1);
        }
        return field;
    }

    @Override
    public Model compile(String sourceCode) {
        CodePointCharStream input = CharStreams.fromString(sourceCode.toUpperCase());
        MySqlLexer lexer = new MySqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        MySqlParser.CreateTableContext ctDdlTree = parser.createTable();
        ParseTreeWalker walker = new ParseTreeWalker();
        TableModel model = new TableModel();
        this.setTableModel(model);
        walker.walk(this, ctDdlTree);
        return model;
    }
}
