package com.upc.ittrainer.view.component;

import org.primefaces.component.api.UIData;
import org.primefaces.component.datatable.DataTable;

public class CustomDataTable extends DataTable {

    @Override
    public String getPaginatorTemplate() {
        return (String) this.getStateHelper().eval(UIData.PropertyKeys.paginatorTemplate,
                "{CurrentPageReport}  {FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink} {RowsPerPageDropdown} {JumpToPageDropdown}");
    }

    @Override
    public String getCurrentPageReportTemplate() {
        return (String) this.getStateHelper().eval(UIData.PropertyKeys.currentPageReportTemplate,
                "[ {startRecord} to {endRecord} of {totalRecords} ]");
    }

    @Override
    public String getRowsPerPageTemplate() {
        return (String) this.getStateHelper().eval(UIData.PropertyKeys.rowsPerPageTemplate,
                "15,30,50,100,1000");
    }

    @Override
    public String getPaginatorPosition() {
        return (String) this.getStateHelper().eval(UIData.PropertyKeys.paginatorPosition,
                "bottom");
    }

    @Override
    public int getRows() {
        return (Integer) this.getStateHelper().eval(
                "rows", 15);
    }

    @Override
    public void setRows(int rows) {
        if (rows < 0) {
            throw new IllegalArgumentException(String.valueOf(rows));
        } else {
            this.getStateHelper().put("rows", rows);
        }
    }

    @Override
    public boolean isMultiViewState() {
        return (Boolean) this.getStateHelper().eval(DataTable.PropertyKeys.multiViewState, true);
    }

    @Override
    public boolean isLazy() {
        return (Boolean) this.getStateHelper().eval(UIData.PropertyKeys.lazy, false);
    }

    @Override
    public boolean isPaginator() {
        return (Boolean) this.getStateHelper().eval(UIData.PropertyKeys.paginator, true);
    }

    @Override
    public boolean isResizableColumns() {
        return (Boolean) this.getStateHelper().eval(DataTable.PropertyKeys.resizableColumns, true);
    }
}
