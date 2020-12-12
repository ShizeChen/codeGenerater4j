import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Example FOR ${_m_comment}
 *
 * Created By @author ${_au}
 * Date ${.now}
 * Generated by codeGenerater4j
 */
public class ${_m_cf}Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ${_m_cf}Example() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class BaseCriteria {

        protected List<Criterion> criteria;

        protected BaseCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }
        <#list _f as filed>

        public Criteria and${filed._f_cf}IsNull() {
            addCriterion("${filed._f_u} is null");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}IsNotNull() {
            addCriterion("${filed._f_u} is not null");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}EqualTo(${filed._f_jt} value) {
            addCriterion("${filed._f_u} =", value, "${filed._f_c}");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}NotEqualTo(${filed._f_jt} value) {
            addCriterion("${filed._f_u} <>", value, "${filed._f_c}");
            return (Criteria) this;
        }
            <#if filed._f_jt="String">

        public Criteria and${filed._f_cf}Like(${filed._f_jt} value) {
            addCriterion("${filed._f_u} like '%${filed._f_c}%'");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}LikeLeft(${filed._f_jt} value) {
            addCriterion("${filed._f_u} like '%${filed._f_c}'");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}LikeRight(${filed._f_jt} value) {
            addCriterion("${filed._f_u} like '${filed._f_c}%'");
            return (Criteria) this;
        }
                <#else>

        public Criteria and${filed._f_cf}GreaterThan(${filed._f_jt} value) {
            addCriterion("${filed._f_u} >", value, "${filed._f_c}");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}GreaterThanOrEqualTo(${filed._f_jt} value) {
            addCriterion("${filed._f_u} >=", value, "${filed._f_c}");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}LessThan(${filed._f_jt} value) {
            addCriterion("${filed._f_u} <", value, "${filed._f_c}");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}LessThanOrEqualTo(${filed._f_jt} value) {
            addCriterion("${filed._f_u} <=", value, "${filed._f_c}");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}In(List<${filed._f_jt}> values) {
            addCriterion("${filed._f_u} in", values, "${filed._f_c}");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}NotIn(List<${filed._f_jt}> values) {
            addCriterion("${filed._f_u} not in", values, "${filed._f_c}");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}Between(${filed._f_jt} value1, ${filed._f_jt} value2) {
            addCriterion("${filed._f_u} between", value1, value2, "${filed._f_c}");
            return (Criteria) this;
        }

        public Criteria and${filed._f_cf}NotBetween(${filed._f_jt} value1, ${filed._f_jt} value2) {
            addCriterion("${filed._f_u} not between", value1, value2, "${filed._f_c}");
            return (Criteria) this;
        }
            </#if>
        </#list>
    }

    public static class Criteria extends BaseCriteria {

        protected Criteria() {
            super();
        }

    }

    public static class Criterion {

        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

}