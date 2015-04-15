'use strict';
angular.module('auctionApp')
.factory('ItemResource', ['$resource',
function($resource) {
   return $resource('/api/item/:itemId', {}, {
      query: {method: 'GET', params:{itemId:''}, isArray:true}
   });
}]);