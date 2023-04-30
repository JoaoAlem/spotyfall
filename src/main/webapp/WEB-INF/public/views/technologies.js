const path = "./public/images/softwares/"
const data = {
    tomcat:{
        title: "Tomcat server",
        image: path + "tomcat.png"
    },
    jakarta:{
        title: "Jakarta 10",
        image: path + "jakarta.jpg"
    },
    jsp:{
        title: "Java Server Pages",
        image: path + "java.png"
    },
    mysql:{
        title: "Mysql Database",
        image: path + "mysql.png"
    },
    vue:{
        title: "VueJS progressive framework",
        image: path + "vuejs.jpeg"
    },
    vueRouter:{
        title: "Vue Router",
        image: path + "vue-router.jpg"
    },
    css:{
        title: "Cascading Style Sheets",
        image: path + "css.png"
    },
    tailwind:{
        title: "Tailwind CSS",
        image: path + "tailwind.png"
    },
    javascript:{
        title: "Javascript",
        image: path + "js.jpg"
    },
    trello:{
        title: "Trello",
        image: path + "trello.jpg"
    },
    penpot:{
        title: "Penpot",
        image: path + "penpot.png"
    },
    azimutt:{
        title: "Azimutt",
        image: path + "azimutt.png"
    }
}

export default {
    template: `
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 max-h-full text-[#f6f6f6] text-center gap-y-5">
        <!-- Card -->
        <template v-for="item in softwareImages">
            <div class="flex flex-col items-center rounded-lg border w-44 h-56 mx-auto p-1" style="border-color: #7700a6">
                <div class="m-3 h-40 flex">
                    <img class="m-auto" :src="item.image"/> 
                </div>
                <p>{{item.title}}</p>
            </div>
        </template>
    </div>
    `,
    data(){
      return {
          softwareImages: data
      }
    }
}
