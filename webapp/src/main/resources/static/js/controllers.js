var auctionControllers = angular.module('auctionControllers', []);

auctionControllers.controller('BidderListController', ['$scope', 'BidderResource',
   function ($scope, BidderResource) {
      $('#bidderQuery').focus();
      $scope.currentPage = 1;
      $scope.itemsPerPage = 10;
      $scope.bidders = BidderResource.query();
      $scope.bidderOnPage = function(index) {
         return (Math.floor(index/$scope.itemsPerPage)+1) == $scope.currentPage;
      }
      $scope.pageChanged = function() {
      }
   }
]);

auctionControllers.controller('BidderController', ['$scope', '$routeParams', 'BidderResource',
   function($scope, $routeParams, BidderResource) {
      $scope.isCreate = ($routeParams.bidderId == -1);
      $('#name').focus();
      $scope.bidder = BidderResource.get({bidderId:$routeParams.bidderId});
      $scope.saveBidder = function() {
         $scope.bidder.$save();
      }
   }
]);

auctionControllers.controller('ItemListController', ['$scope', 'ItemResource',
   function ($scope, ItemResource) {
      $('#itemQuery').focus();
      $scope.currentPage = 1;
      $scope.itemsPerPage = 10;
      $scope.items = ItemResource.query();
      $scope.itemOnPage = function(index) {
         return (Math.floor(index/$scope.itemsPerPage)+1) == $scope.currentPage;
      }
   }
]);

auctionControllers.controller('ItemController', ['$scope', '$routeParams', 'ItemResource',
   function($scope, $routeParams, ItemResource) {
      $scope.item = ItemResource.get({itemId:$routeParams.itemId});
      $('#name').focus();
      $scope.saveItem = function() {
         $scope.item.$save();
      }
   }
]);

auctionControllers.controller('PaymentController', ['$scope', '$timeout', '$routeParams', 'BidderResource', 'PaymentResource', 'InvoiceResource',
   function($scope, $timeout, $routeParams, BidderResource, PaymentResource, InvoiceResource) {
      $scope.bidderId = $routeParams.bidderId;
      $scope.getBidder = function() {
         if ( !$scope.bidderId ) return;
         $scope.bidder = BidderResource.get({bidderId:$scope.bidderId},
         function(bidder) {
            $scope.bidderId = bidder.id;
            $('#method').focus();
         },
         function(response) {
            $scope.error=response.data.exception;
            $('#bidderId').focus();
         });
         $scope.invoice = InvoiceResource.query({bidderId:$scope.bidderId},
         function(invoice) {
            $scope.computeBalance();
            $('#method').focus();
         },
         function(response) {
            $scope.error=response.data.exception;
             $timeout(function() {
                $('#bidderId').focus();
             }, 100);
         });
      }

      $scope.computeBalance = function() {
         $scope.balance = 0;
         $scope.invoice.forEach( function(line) {
            $scope.balance += line.amount;
         });
      }

      $scope.clearBidder = function() {
         $scope.bidder = null;
         $scope.reference = null;
         $scope.amount = null;
         $scope.error = null;
         $scope.invoice = null;
      }

      $scope.addPayment = function() {
         if ( !$scope.bidderId || !$scope.amount ) return;
         $scope.payment = new PaymentResource({bidderId:$scope.bidderId, method:$scope.method, reference:$scope.reference, amount:$scope.amount});
         $scope.payment.$save({}, function(payment) {
            $scope.getBidder();
            $timeout(function() {
               $('#bidderId').focus();
            }, 100);
         }, function(response) {
            $scope.error = response.data.exception;
         });
      }

      $scope.allowPayments = function() {
         return true;
      }
      $scope.getBidder();
   }
]);

auctionControllers.controller('WinningBidController', ['$scope', '$timeout', 'ItemResource', 'BidderResource', 'BidResource',
   function($scope, $timeout, ItemResource, BidderResource, BidResource) {
      $('#itemId').focus();

      $scope.getItem = function() {
         if ( !$scope.itemId ) return;
         $scope.item = ItemResource.get({itemId:$scope.itemId},
         function(item) {
            $('#bidderId').focus();
         },
         function(response) {
            $scope.error=response.data.exception;
            $('#itemId').focus();
         });
      }

      $scope.clearItem = function() {
         $scope.item = null;
         $scope.error = null;
      }

      $scope.getBidder = function() {
         if ( !$scope.bidderId ) return;
         $scope.bidder = BidderResource.get({bidderId:$scope.bidderId},
         function(bidder) {
            $('#amount').focus();
         },
         function(response) {
            $scope.error=response.data.exception;
            $('#bidderId').focus();
         });
      }

      $scope.clearBidder = function() {
         $scope.amount = null;
         $scope.bidder = null;
         $scope.error = null;
      }

      $scope.addBid = function() {
         if ( !$scope.bidderId || !$scope.amount ) return;
         $scope.bid = new BidResource({itemId:$scope.itemId, bidderId:$scope.bidderId, amount:$scope.amount});
         $scope.bid.$save({}, function(bid) {
            $scope.item.winningBids.push(bid)
            $scope.clearBidder();
            $scope.bidderId = null;
         }, function(response) {
            $scope.error = response.data.exception;
         });
         $timeout(function() {
            $('#bidderId').focus();
         }, 100);
      }

      $scope.allowBids = function() {
         if (!$scope.item || !$scope.item.winningBids) return true;
         return $scope.item && $scope.item.winningBids.length < $scope.item.maxWinners
      }
   }
]);
