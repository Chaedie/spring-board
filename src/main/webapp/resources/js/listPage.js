const $pagenation = document.querySelector(".datatable-pagination-list");
const lastPage = Math.floor(totalCount / size);
let pageListNum = Math.floor((page) / 10) * 10;
const startPageIdx = 1 + pageListNum;
const lastPageIdx = lastPage <= 10 + pageListNum ? lastPage : 10 + pageListNum;
let innerHTML = "";

innerHTML += `
      <li class="datatable-pagination-list-item">
        <a href="?page=${page - 10 > 0 ? page - 10 : 0}&size=${size}&search=${search}&sort=${sort}"
          data-page="1" class="datatable-pagination-list-item-link">‹</a>
      </li>`;

for (let i = startPageIdx; i <= lastPageIdx + 1; i++) {
  innerHTML += `
          <li class="datatable-pagination-list-item ${page == i - 1 ? "datatable-active" : ""} ">
            <a href="?page=${i - 1}&size=${size}&search=${search}&sort=${sort}" 
              data-page="${i - 1}" class="datatable-pagination-list-item-link">${i}</a>
          </li>
          `;
}
innerHTML += `
      <li class="datatable-pagination-list-item"  >
        <a href="?page=${page + 10 < lastPage ? page + 10 : lastPage}&size=${size}&search=${search}&sort=${sort}" 
          data-page="2" class="datatable-pagination-list-item-link">›</a>
      </li>`;
$pagenation.innerHTML = innerHTML;

$sizeSelector = document.querySelector(".datatable-dropdown");
const sizeList = [10, 15, 20, 25, 50, 100];
let selectorInnerHTML = `
      <label>
          <select class="datatable-selector">
            ${sizeList.map((value) => (
    `<option value='${value}' ${size == value ? 'selected' : ''}>${value}</option>`
)).join("")}
          </select> entries per page
      </label>`;

$sizeSelector.innerHTML = selectorInnerHTML;
$sizeSelector.addEventListener('change', (e) => {
  const value = e.target.value;
  location.href = `?page=${page}&size=${value}&search=${search}&sort=${sort}`;
});

$searchForm = document.querySelector(".searchForm");
$searchForm.addEventListener('submit', (e) => {
  e.preventDefault();
  const value = e.target.searchInput.value;
  location.href = `?page=0&size=10&search=${value}&sort=${sort}`;
})

const handleClickColumn = (column, sortable) => {
  if (!sortable) {
    return;
  }
  const ordering = sort.split(",")[1];
  const newOrdering = ordering == "DESC" ? "ASC" : "DESC";

  location.href = `?page=${page}&size=${size}&search=${search}&sort=${column},${newOrdering}`;
}
const $columnHead = document.querySelector("#columnHead");

let columnHeadInnerHTML = '';
for (let header of columnHeaderList) {
  const {id, entityColumnName, listColumnName, sortable} = header;
  columnHeadInnerHTML +=
      `<th data-sortable="${sortable}" style="${sortable == "true" ? "cursor: pointer" : ""}"><a class="datatable-sorter" onclick="handleClickColumn('${entityColumnName}', ${sortable})">${listColumnName}</a></th>`
}
$columnHead.innerHTML = columnHeadInnerHTML;
