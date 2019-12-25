let cartJS = {
    addProductInCart: function(product) {
        let cart = cartJS.getCart();
        let commonCount = cartJS.getCommonCount();
        let commonPrice = cartJS.getCommonPrice();

        let price = 0;
        let find = false;
        for(let i = 0; i < cart.length; i++) {
            if(cart[i]['article_clothes'] == product['article_clothes'] && cart[i]['size'] == product['size']) {
                find = true;
                cart[i]['count'] = +cart[i]['count'] + 1;
                break;
            }
        }
        if(!find)  {
            product['count'] = 1;
            cart.push(product);
        }
        console.log("добавлем", cart);
        localStorage.setItem("cart", JSON.stringify(cart));
        localStorage.setItem("count", commonCount + 1);
        localStorage.setItem("price", commonPrice + +product['price']);
    },

    getCart: function() {
        let cart = JSON.parse(localStorage.getItem("cart"));
        if(cart == null) {
            cart = [];
        }
        return cart;
    },

    getObjCart: function() {
        let cart = cartJS.getCart();
        let objCart = {};
        for(let i = 0; i < cart.length; i++) {
            if(objCart[cart[i]['article_clothes']] == null) {
                objCart[cart[i]['article_clothes']] = {};
            }
            objCart[cart[i]['article_clothes']][cart[i]['size']] = cart[i]['count'];
        }
        console.log(objCart);
        return objCart;
    },

    getProductInCart: function(article) {
        let cart = cartJS.getCart();
        let objCart = {};
        for(let i = 0; i < cart.length; i++) {
            if(article == cart[i]['article_clothes']) {
                objCart[cart[i]['size']] = cart[i]['count'];
            }
        }
        return objCart;
    },

    getCommonCount: function() {
        let commonCount = localStorage.getItem("count");
        if(commonCount == null) {
            commonCount = 0;
        }
        else {
            commonCount = +commonCount;
        }
        return commonCount;
    },

    getCommonPrice: function() {
        let commonPrice = localStorage.getItem("price");
        if(commonPrice == null) {
            commonPrice = 0;
        }
        else {
            commonPrice = +commonPrice;
        }
        return commonPrice;
    },

    deleteProductFromCart: function(product) {
        let cart = cartJS.getCart();
        let find = false;

        let commonPrice = +localStorage.getItem("price");
        let commonCount = +localStorage.getItem("count");
        for(let i = 0; i < cart.length; i++) {
            if(cart[i]['article_clothes'] == product['article_clothes'] && cart[i]['size'] == product['size']) {
                find = true;
                commonPrice -= (+cart[i]['price'] * +cart[i]['count']);
                commonCount -= +cart[i]['count'];
                cart.splice(i, 1);
                break;
            }
        }
        localStorage.setItem("cart", JSON.stringify(cart));
        localStorage.setItem("count", commonCount);
        localStorage.setItem("price", commonPrice);

    },

    getCountProductsInCart: function() {
        let count = 0;
        let cart = cartJS.getCart();
        for(let i = 0; i < cart.length; i++) {
            count += +cart[i]['count'];
        }
        return count;
    },

    setCountInNavbar: function() {
        let count = cartJS.getCountProductsInCart();
        if(count != 0) {
            $('#count-in-cart').html(count);
        }
        else {
            $('#count-in-cart').html('');
        }
    },

    clearCart: function() {
        localStorage.clear();
    }
};

//cartJS.clearCart();
cartJS.setCountInNavbar();