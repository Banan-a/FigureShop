<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@page
contentType="text/html" pageEncoding="UTF-8"%>

<div class="flex flex-col w-full p-5">
  <div class="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
    <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
      <div
        class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg"
      >
        <table class="min-w-full divide-y divide-gray-200">
          <thead class="bg-gray-50">
            <tr>
              <th
                scope="col"
                class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
              >
                Full sname
              </th>
              <th
                scope="col"
                class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
              >
                Email
              </th>
              <th
                scope="col"
                class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
              >
                Create date
              </th>
              <th
                scope="col"
                class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
              >
                Status
              </th>
              <th scope="col" class="relative px-6 py-3">
                <span class="sr-only">View</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <!-- Odd row -->
            <tr class="bg-white">
              <td
                class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"
              >
                Jane Cooper
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                Regional Paradigm Technician
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                jane.cooper@example.com
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                Admin
              </td>
              <td
                class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium"
              >
                <a href="#" class="text-indigo-600 hover:text-indigo-900"
                  >Edit</a
                >
              </td>
            </tr>

            <!-- Even row -->
            <tr class="bg-gray-50">
              <td
                class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"
              >
                Cody Fisher
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                Product Directives Officer
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                cody.fisher@example.com
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                Owner
              </td>
              <td
                class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium"
              >
                <a href="#" class="text-indigo-600 hover:text-indigo-900"
                  >Edit</a
                >
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
