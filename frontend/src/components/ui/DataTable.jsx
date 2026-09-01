import React, { useState, useMemo } from 'react';
import { Search, ChevronLeft, ChevronRight, ArrowUpDown, Download, Filter } from 'lucide-react';

export function DataTable({
  columns = [],
  data = [],
  title,
  searchPlaceholder = 'Search records...',
  actions,
  onRowClick,
  pageSize = 8
}) {
  const [search, setSearch] = useState('');
  const [sortField, setSortField] = useState(null);
  const [sortAsc, setSortAsc] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);

  // Filter
  const filteredData = useMemo(() => {
    if (!search.trim()) return data;
    const term = search.toLowerCase();
    return data.filter((row) =>
      columns.some((col) => {
        const val = row[col.accessor];
        return val != null && String(val).toLowerCase().includes(term);
      })
    );
  }, [data, search, columns]);

  // Sort
  const sortedData = useMemo(() => {
    if (!sortField) return filteredData;
    return [...filteredData].sort((a, b) => {
      const aVal = a[sortField];
      const bVal = b[sortField];
      if (aVal == null) return 1;
      if (bVal == null) return -1;
      if (typeof aVal === 'number' && typeof bVal === 'number') {
        return sortAsc ? aVal - bVal : bVal - aVal;
      }
      return sortAsc
        ? String(aVal).localeCompare(String(bVal))
        : String(bVal).localeCompare(String(aVal));
    });
  }, [filteredData, sortField, sortAsc]);

  // Pagination
  const totalPages = Math.ceil(sortedData.length / pageSize) || 1;
  const paginatedData = useMemo(() => {
    const start = (currentPage - 1) * pageSize;
    return sortedData.slice(start, start + pageSize);
  }, [sortedData, currentPage, pageSize]);

  const handleSort = (accessor) => {
    if (sortField === accessor) {
      setSortAsc(!sortAsc);
    } else {
      setSortField(accessor);
      setSortAsc(true);
    }
  };

  const exportCSV = () => {
    if (!sortedData.length) return;
    const headers = columns.map((col) => col.header).join(',');
    const rows = sortedData.map((row) =>
      columns.map((col) => `"${String(row[col.accessor] ?? '').replace(/"/g, '""')}"`).join(',')
    );
    const csvContent = 'data:text/csv;charset=utf-8,' + [headers, ...rows].join('\n');
    const encodedUri = encodeURI(csvContent);
    const link = document.createElement('a');
    link.setAttribute('href', encodedUri);
    link.setAttribute('download', `${title || 'export'}_${Date.now()}.csv`);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  return (
    <div className="bg-white border border-slate-200 rounded-2xl shadow-sm overflow-hidden">
      {/* Top Header & Search Bar */}
      <div className="p-4 border-b border-slate-200 flex flex-col md:flex-row md:items-center justify-between gap-3 bg-slate-50/50">
        <div>
          {title && <h3 className="text-sm font-bold text-slate-900 tracking-tight">{title}</h3>}
          <p className="text-[11px] text-slate-500 font-medium mt-0.5">Showing {sortedData.length} records</p>
        </div>

        <div className="flex flex-wrap items-center gap-2.5">
          <div className="relative">
            <Search className="w-4 h-4 text-slate-400 absolute left-3 top-1/2 transform -translate-y-1/2" />
            <input
              type="text"
              placeholder={searchPlaceholder}
              value={search}
              onChange={(e) => {
                setSearch(e.target.value);
                setCurrentPage(1);
              }}
              className="pl-9 pr-3 py-1.5 bg-white border border-slate-200 rounded-xl text-xs text-slate-900 placeholder-slate-400 focus:outline-none focus:border-blue-500 transition-all w-52 md:w-64 font-medium"
            />
          </div>

          <button
            onClick={exportCSV}
            className="flex items-center px-3 py-1.5 bg-white hover:bg-slate-50 border border-slate-200 text-slate-700 text-xs font-bold rounded-xl transition-all shadow-2xs"
            title="Export CSV"
          >
            <Download className="w-3.5 h-3.5 mr-1.5 text-blue-600" />
            Export CSV
          </button>

          {actions}
        </div>
      </div>

      {/* Table Element */}
      <div className="overflow-x-auto">
        <table className="w-full text-left text-xs">
          <thead className="bg-slate-50 text-slate-500 uppercase tracking-wider font-bold border-b border-slate-200">
            <tr>
              {columns.map((col) => (
                <th
                  key={col.accessor || col.header}
                  onClick={() => col.sortable !== false && handleSort(col.accessor)}
                  className={`px-4 py-3 ${col.sortable !== false ? 'cursor-pointer hover:text-slate-800 transition-colors select-none' : ''}`}
                >
                  <div className="flex items-center space-x-1">
                    <span>{col.header}</span>
                    {col.sortable !== false && (
                      <ArrowUpDown className={`w-3 h-3 ${sortField === col.accessor ? 'text-blue-600' : 'opacity-40'}`} />
                    )}
                  </div>
                </th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-100 text-slate-800 font-medium">
            {paginatedData.length > 0 ? (
              paginatedData.map((row, idx) => (
                <tr
                  key={row.id || idx}
                  onClick={() => onRowClick && onRowClick(row)}
                  className={`hover:bg-slate-50 transition-colors ${onRowClick ? 'cursor-pointer' : ''}`}
                >
                  {columns.map((col) => (
                    <td key={col.accessor || col.header} className="px-4 py-3 whitespace-nowrap">
                      {col.render ? col.render(row[col.accessor], row) : (row[col.accessor] ?? '—')}
                    </td>
                  ))}
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={columns.length} className="px-4 py-8 text-center text-slate-400 font-normal">
                  No matching records found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {/* Pagination Footer */}
      {totalPages > 1 && (
        <div className="p-3 border-t border-slate-200 flex items-center justify-between text-xs text-slate-500 bg-slate-50/30">
          <div>
            Page <span className="font-bold text-slate-800">{currentPage}</span> of{' '}
            <span className="font-bold text-slate-800">{totalPages}</span>
          </div>
          <div className="flex items-center space-x-1.5">
            <button
              disabled={currentPage === 1}
              onClick={() => setCurrentPage((p) => Math.max(1, p - 1))}
              className="p-1.5 rounded-lg border border-slate-200 bg-white hover:bg-slate-50 disabled:opacity-40 disabled:cursor-not-allowed transition-all text-slate-700"
            >
              <ChevronLeft className="w-4 h-4" />
            </button>
            <button
              disabled={currentPage === totalPages}
              onClick={() => setCurrentPage((p) => Math.min(totalPages, p + 1))}
              className="p-1.5 rounded-lg border border-slate-200 bg-white hover:bg-slate-50 disabled:opacity-40 disabled:cursor-not-allowed transition-all text-slate-700"
            >
              <ChevronRight className="w-4 h-4" />
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
