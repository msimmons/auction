'use strict';
angular.module('auctionApp')
.controller('BidderController', ['$scope', '$routeParams', 'BidderResource',
   function($scope, $routeParams, BidderResource) {
      $scope.isCreate = ($routeParams.bidderId == -1);
      $('#name').focus();
      $scope.bidder = BidderResource.get({bidderId:$routeParams.bidderId});
      $scope.saveBidder = function() {
         $scope.bidder.$save();
      }
   }
]);
