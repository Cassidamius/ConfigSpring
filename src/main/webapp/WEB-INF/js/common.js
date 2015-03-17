function searchList() {
	$("#searchForm").submit();
}

function deleteItem(url,id) {
	$("#searchForm").attr("action", url + "?id=" + id);
	$("#searchForm").submit();
}

function toEditPage(url,id) {
	$("#searchForm").attr("action", url + "?id=" + id);
	$("#searchForm").submit();
}

function changePage(currentPage,totalRow) {
	$("#currentPage").val(currentPage);
	$("#pageSize").val($("#pageSizeSelect option:selected").val());
	$("#totalRow").val(totalRow);
	$("#searchForm").attr("method", "post");
	$("#searchForm").submit();
}

function toAddPage(url) {
	$("#searchForm").attr("action", "${pageContext.request.contextPath}" + url);
	$("#searchForm").submit();
}

function edit() {
	$("#editForm").submit();
}