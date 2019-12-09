# Ngrx2

## App State
```json
{
  "appState": {
    "userState": {
        "username": "ashim",
        "email": "ashim.khadka@clusus.com"
    }
  }
}
```

## Modular
FeatureState
  - indv301 - state - indvReducer
  - indvDetail - state - indvReducer

fetch - data load from api
retriever - store
eventhandler - drill to other page

LOAD_GRID - action - reusable, complexity, non-modular

LOAD_301_GRID - action - repetitive_code, simplicity, modular

