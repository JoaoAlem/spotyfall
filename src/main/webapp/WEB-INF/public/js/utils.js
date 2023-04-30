/**
 *
 * @param model
 * @param source
 * @param vars
 * @returns cloneModel
 */
export function define(model, source, vars){
    if(Array.isArray(source)){
        return source.map(item => define(model, item, vars))
    }else{
        let cloneModel = model
        for(let item in vars){
            if(source && typeof(source[item]) !== 'undefined'){
                cloneModel[item] = source[item]
                continue
            }

            cloneModel[item] = null
        }
        return cloneModel;
    }
}

/**
 *
 * @param route
 * @param callbackSuccess
 * @param callbackError
 */
async function request(route){
    return await axios.get(route);
}

export default request