package com.codegenerater.compile;

import com.codegenerater.antlr.hive.HiveLexer;
import com.codegenerater.antlr.hive.HiveParser;
import com.codegenerater.antlr.hive.HiveParserBaseListener;
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
public class AntlrHiveCompiler extends HiveParserBaseListener {

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
    public void enterTableName(HiveParser.TableNameContext ctx) {
        tableModel.setName(extractContent(ctx));
    }

    @Override
    public void enterColumnNameTypeConstraint(HiveParser.ColumnNameTypeConstraintContext ctx) {
        TableField currentFiled = getCursor();
        ParseTree dataNode = ctx.getChild(HiveParser.IdentifierContext.class, 0);
        ParseTree dataNameNode = dataNode.getChild(0);
        Optional.ofNullable(dataNameNode).ifPresent(node -> currentFiled.setName((extractContent(node))));
        ParseTree dataNode1 = ctx.getChild(1);
        ParseTree dataTypeNode = dataNode1.getChild(0);
        Optional.ofNullable(dataTypeNode).ifPresent(node -> currentFiled.setType(FiledType.getByHqlType(extractContent(node))));
        ParseTree commentNode = ctx.getChild( 3);
        Optional.ofNullable(commentNode).ifPresent(node -> currentFiled.setComment(extractContent(node)));
    }

    @Override
    public void exitColumnNameTypeConstraint(HiveParser.ColumnNameTypeConstraintContext ctx) {
        tableModel.addTableFiled(getCursor());
        resetCursor();
    }

    @Override
    public void enterTableComment(HiveParser.TableCommentContext ctx) {
        ParseTree tbCommentNode = ctx.children.get(1);
        Optional.ofNullable(tbCommentNode).ifPresent(node -> tableModel.setDesc(extractContent(node)));
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
        HiveLexer lexer = new HiveLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HiveParser parser = new HiveParser(tokens);
        HiveParser.CreateTableStatementContext ctDdlTree = parser.createTableStatement();
        ParseTreeWalker walker = new ParseTreeWalker();
        TableModel model = new TableModel();
        this.setTableModel(model);
        walker.walk(this, ctDdlTree);
        return model;
    }
}
