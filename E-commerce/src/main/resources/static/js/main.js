const createProductHtml = (productData) => {
  const template = `
      <div>
        <h4>${productData.name}</h4>
        <span>${productData.price}</span>
        <button data-id="${productData.id}"></button>
      </div>
  `
  const productElement = document.createElement('li');
  productElement.innerHTML = template.trim()

  return productElement;
};

const getProducts = () => {
  return fetch("/api/products")
      .then(response => response.json())
}

document.addEventListener("DOMContentLoaded", () => {
  const productListElement = document.querySelector("#productsList");
  getProducts()
      .then(productsAsJson => productsAsJson.map(createProductHtml))
      .then(productsAsHtml => {
        productsAsHtml.forEach(productElement => productListElement.appendChild(productElement))
      })
});