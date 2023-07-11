const $pagenation = document.querySelector(".datatable-pagination-list");
const lastPage = Math.floor(totalCount / size) + 1;
let pageListNum = Math.floor((page - 1) / 10) * 10;
const startPageIdx = 1 + pageListNum;
const lastPageIdx = lastPage <= 10 + pageListNum ? lastPage : 10 + pageListNum;
let innerHTML = "";

innerHTML += `
      <li class="datatable-pagination-list-item">
        <a href="?page=${page - 10 > 0 ? page - 10 : 1}&size=${size}&search=${search}"data-page="1" class="datatable-pagination-list-item-link">‹</a>
      </li>`;

for (let i = startPageIdx; i <= lastPageIdx; i++) {
  innerHTML += `
          <li class="datatable-pagination-list-item ${page == i ? "datatable-active" : ""} ">
            <a href="?page=${i}&size=${size}&search=${search}" data-page="${i}" class="datatable-pagination-list-item-link">${i}</a>
          </li>
          `;
}
innerHTML += `
      <li class="datatable-pagination-list-item"  >
        <a href="?page=${page + 10 < lastPage ? page + 10 : lastPage}&size=${size}&search=${search}" data-page="2" class="datatable-pagination-list-item-link">›</a>
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
  location.href = `customerList?page=${page}&size=${value}&search=${search}`;
});

$searchForm = document.querySelector(".searchForm");
$searchForm.addEventListener('submit', (e) => {
  e.preventDefault();
  const value = e.target.searchInput.value;
  location.href = `customerList?page=1&size=10&search=${value}`;
})

const handleClickColumn = (column) => {
  const newOrdering = ordering == "desc" ? "asc" : "desc";
  location.href = `customerList?page=${page}&size=${size}&search=${search}&orderBy=${column}&ordering=${newOrdering}`;
}
const $columnHead = document.querySelector("#columnHead");
$columnHead.innerHTML = `
                <tr>
                  <th data-sortable="true" style="width: 2rem;><a class="datatable-sorter" onclick="handleClickColumn('c_id')">글 ID</a></th>
                  <th data-sortable="true" style="width: 10rem;><a class="datatable-sorter" onclick="handleClickColumn('c_name')">글 제목</a></th>
                  <th data-sortable="true" style="width: 2rem;><a class="datatable-sorter" onclick="handleClickColumn('c_gender')">작성일</a></th>
                  <th data-sortable="true" style="width: 2rem;><a class="datatable-sorter" onclick="handleClickColumn('c_rrn')">수정일</a></th>
                </tr>`;
