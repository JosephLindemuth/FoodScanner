class FavoritesItem() {
    var upc: String = ""
    var favScanned: String = ""

    constructor(upc: String) : this()
    {
        this.upc = upc
    }

    constructor(upc: String, scanned: String) : this(upc)
    {
        this.favScanned = scanned
    }
}