'use strict';
angular.module('auctionApp')
.factory('BidResource', ['$resource',
function($resource) {
   return $resource('/api/bid', {}, {
   });
}]);