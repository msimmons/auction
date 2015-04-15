'use strict';
angular.module('auctionApp')
.factory('InvoiceResource', ['$resource',
function($resource) {
   return $resource('/api/invoice/:bidderId', {}, {
      query: {method: 'GET', params:{bidderId:''}, isArray:true}
   });
}]);