# api-prices

## Endpoint

```
curl --location --request GET 'http://localhost:8080/prices/apply?appliedDate=2020-06-14T21:00:00.000Z&productId=35455&brandId=1' \
--data-raw ''
```

## Sample response

```
{
    "brand_id": 1,
    "start_date": "2020-06-14T00:00:00",
    "end_date": "2020-12-31T23:59:59",
    "product_id": 35455,
    "price": 35.5
}
```
