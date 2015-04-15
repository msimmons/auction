'use strict';
angular.module('auctionApp')
.factory('BidderResource', ['$resource',
function($resource) {
   return $resource('/api/bidder/:bidderId', {}, {
      query: {method: 'GET', params:{bidderId:''}, isArray:true}
   });
}]);