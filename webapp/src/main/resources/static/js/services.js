var auctionServices = angular.module('auctionServices', []);

auctionServices.factory('ItemResource', ['$resource',
function($resource) {
   return $resource('/api/item/:itemId', {}, {
      query: {method: 'GET', params:{itemId:''}, isArray:true}
   });
}]);

auctionServices.factory('BidderResource', ['$resource',
function($resource) {
   return $resource('/api/bidder/:bidderId', {}, {
      query: {method: 'GET', params:{bidderId:''}, isArray:true}
   });
}])

auctionServices.factory('BidResource', ['$resource',
function($resource) {
   return $resource('/api/bid', {}, {
   });
}])